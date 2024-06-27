/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.utils;


import com.fulinx.spring.core.utils.orika.LocalDateConverter;
import com.fulinx.spring.core.utils.orika.LocalDateTimeConverter;
import com.fulinx.spring.core.utils.orika.LocalTimeConverter;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Slf4j
public class MiscUtils {


    /**
     * copyProperties:复制一个bean到另一个bean. <br/>
     *
     * @author michaelgu
     * @param s
     * @param destClazz
     * @return
     * @since JDK 1.8
     */
    public static <S, D> D copyProperties(S s, Class<D> destClazz){
        try {
            D dest = destClazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(s, dest);
            return dest;
        }  catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("org.springframework.beans.BeanUtils copy list failed, illegal access exception or instantiation exception.");
        }
    }

    /**
     * copyList:复制一个集合到另一个集合. <br/>
     *
     * @author michaelgu
     * @param collections
     * @param destClazz
     * @return
     * @since JDK 1.8
     */
    public static <T> List<T> copyList(Collection<?> collections, Class<T> destClazz){
        List<T> list = new ArrayList<>(collections.size());
        for(Object source : collections){
            try{
                T dest = destClazz.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(source, dest);
                list.add(dest);
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException("org.springframework.beans.BeanUtils copy list failed, illegal access exception or instantiation exception.");
            }
        }
        return list;
    }

    /**
     * copyProperties:复制一个bean到另一个bean，可以指定属性的映射关系. <br/>
     *
     * @author michaelgu
     * @param s
     * @param destClazz
     * @return
     * @since JDK 1.8
     */
    public static <S, D> D copyProperties(S s, Class<D> destClazz, Map<String, String> fieldMap){
        if(s == null){
            return null;
        }
        MapperFactory mapperFactory = mapperFactory();
        ClassMapBuilder<?, ?> classMapBuilder = mapperFactory.classMap(s.getClass(), destClazz);
        for(Map.Entry<String, String> entry : fieldMap.entrySet()){
            classMapBuilder.field(entry.getKey(), entry.getValue());
        }
        classMapBuilder.byDefault();
        classMapBuilder.register();
        return mapperFactory.getMapperFacade().map(s, destClazz);
    }

    /**
     * copyProperties:复制一个bean到另一个bean. <br/>
     *
     * @author michaelgu
     * @param source
     * @param target
     * @return
     * @since JDK 1.8
     */
    public static Object copyProperties(Object source, Object target){
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * subList:按照blockSize分割成若干个subList. <br/>
     *
     * @author michaelgu
     * @param list
     * @param blockSize
     * @return
     * @since JDK 1.8
     */
    public static <T> List<List<T>> subList(List<T> list, int blockSize) {
        List<List<T>> lists = new ArrayList<>();
        if (list != null && blockSize > 0) {
            int listSize = list.size();
            if (listSize <= blockSize) {
                lists.add(list);
                return lists;
            }
            int batchSize = listSize / blockSize;
            int remain = listSize % blockSize;
            for (int i = 0; i < batchSize; i++) {
                int fromIndex = i * blockSize;
                int toIndex = fromIndex + blockSize;
                lists.add(list.subList(fromIndex, toIndex));
            }
            if (remain > 0) {
                lists.add(list.subList(listSize - remain, listSize));
            }
        }
        return lists;
    }

    private static MapperFactory mapperFactory(){
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(new LocalDateTimeConverter());
        converterFactory.registerConverter(new LocalDateConverter());
        converterFactory.registerConverter(new LocalTimeConverter());
        return mapperFactory;
    }



    public static String[] shortUrl(String salt, String url) {

        // 要使用生成 URL 的字符
        String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
                "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
                "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z"
        };

        String hex = CMyEncrypt.md5(salt + url);

        String[] resUrl = new String[4];
        for (int i = 0; i < 4; i++) {
            // 把加密字符按照8位一组16进制与0x3FFFFFFF进行位与运算
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);

            // 这里需要使用 long 型来转换，因为 Inteter.parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            String outChars = "";
            for (int j = 0; j < 6; j++) {
                long index = 0x0000003D & lHexLong;     // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                outChars += chars[(int) index];         // 把取得的字符相加
                lHexLong = lHexLong >> 5;             // 每次循环按位右移 5 位
            }
            resUrl[i] = outChars;                       // 把字符串存入对应索引的输出数组
        }
        return resUrl;
    }

    private static class CMyEncrypt {
        // 十六进制下数字到字符的映射数组
        private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
        /** 把inputString加密 */
        public static String md5(String inputStr) {
            return encodeByMD5(inputStr);
        }
        /**
         * 验证输入的密码是否正确
         *
         * @param password 真正的密码（加密后的真密码）
         * @param inputString 输入的字符串
         * @return 验证结果，boolean类型
         */
        public static boolean authenticatePassword(String password, String inputString) {
            if (password.equals(encodeByMD5(inputString))) {
                return true;
            } else {
                return false;
            }
        }

        /** 对字符串进行MD5编码 */
        private static String encodeByMD5(String originString) {
            if (originString != null) {
                try {
                    // 创建具有指定算法名称的信息摘要
                    MessageDigest md5 = MessageDigest.getInstance("MD5");
                    // 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                    byte[] results = md5.digest(originString.getBytes());
                    // 将得到的字节数组变成字符串返回
                    String result = byteArrayToHexString(results);
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        /**
         * 轮换字节数组为十六进制字符串
         *
         * @param b 字节数组
         * @return 十六进制字符串
         */
        private static String byteArrayToHexString(byte[] b) {
            StringBuffer resultSb = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                resultSb.append(byteToHexString(b[i]));
            }
            return resultSb.toString();
        }

        // 将一个字节转化成十六进制形式的字符串
        private static String byteToHexString(byte b) {
            int n = b;
            if (n < 0) {
                n = 256 + n;
            }
            int d1 = n / 16;
            int d2 = n % 16;
            return hexDigits[d1] + hexDigits[d2];
        }
    }
}
