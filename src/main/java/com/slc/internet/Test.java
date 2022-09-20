package com.slc.internet;

import cn.hutool.core.io.FileUtil;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Test {
    static final Pattern p = Pattern.compile("^[0-9]*\\..*");

    public static void main(String[] args) {
        List<String> list = FileUtil.readLines("C:\\Users\\18484\\Desktop\\txt\\重生为君-原.txt", StandardCharsets.UTF_8);
        List<Object> convertList = list.stream().map(str -> {
            Matcher matcher = p.matcher(str);
            if (matcher.matches()) {
                return str.replaceAll("(^[0-9]*)(\\.)(.*)", "第 $1 章$2 $3 ");
            }
            return str;
        }).collect(Collectors.toList());
        FileUtil.writeLines(convertList,"C:\\Users\\18484\\Desktop\\txt\\重生为君.txt", StandardCharsets.UTF_8);
    }
}
