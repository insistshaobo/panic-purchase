package utils;

import exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangshaobo
 * @version 8.4.15
 * @Description: 地址解析省市区
 * @date 2023/10/7 15:08
 */
public class AddressUtil {

    private AddressUtil() {}

    /**
     * 从地址串中解析提取出省市区等信息
     * @param address   地址信息
     * @return          解析后的地址Map
     */
    private static Map<String,String> addressResolution(String address){
        //1.地址的正则表达式
        String regex = "(?<province>[^省]+省|.+自治区|[^澳门]+澳门|[^香港]+香港|[^市]+市)?(?<city>[^自治州]+自治州|[^特别行政区]+特别行政区|[^市]+市|.*?地区|.*?行政单位|.+盟|市辖区|[^县]+县)(?<county>[^县]+县|[^市]+市|[^镇]+镇|[^区]+区|[^乡]+乡|.+场|.+旗|.+海域|.+岛)?(?<address>.*)";
        //2、创建匹配规则
        Matcher m = Pattern.compile(regex).matcher(address);
        String province;
        String city;
        String county;
        String detailAddress;
        Map<String,String> map = new HashMap<>(16);

        while (m.find()){
            //加入省
            province = m.group("province");
            map.put("province", province == null ? "" : province.trim());
            //加入市
            city = m.group("city");
            map.put("city", city == null ? "" : city.trim());
            //加入区
            county = m.group("county");
            map.put("county", county == null ? "" : county.trim());
            //详细地址
            detailAddress = m.group("address");
            map.put("address", detailAddress == null ? "" : detailAddress.trim());
        }
        return map;
    }

    /**
     * 根据地址获取解析后的地址对象
     * @param address   解析前地址Str
     * @return          解析后地址对象
     */
    public static Map resolveAddress(String address) {
        if (StringUtils.isBlank(address)) {
            throw new ServiceException("客户联系地址出错");
        }
        Map<String, String> addressMap = addressResolution(address);
        return addressMap;
    }

//    public static void main(String[] args) {
//        Map map = resolveAddress("河北省石家庄市桥西区维明南大街");
//        System.out.println(map);
//
//    }

    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();
        // 创建FileReader对象来读取文件
        FileReader fileReader = new FileReader("C:\\Users\\PC\\Desktop\\地址1007.txt");
        // 创建BufferedReader对象来读取文件内容
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        // 逐行读取文件内容并打印
        while ((line = bufferedReader.readLine()) != null) {
            list.add(line);
        }
        String filePath = "C:\\Users\\PC\\Desktop\\地址sql.txt";
        StringBuilder urlResult = new StringBuilder();
        for(String s : list) {
            try {
                Map map = resolveAddress(s);
                urlResult.append(map.get("province")).append("'").append(",").append("'").append(map.get("city")).append("'").append(",").append("'").append(map.get("county")).append("'").append(",").append("'").append(map.get("address")).append("');");
                urlResult.append(System.lineSeparator());
            } catch (Exception e) {
                System.out.println("文件未获取到");
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println(urlResult);
            System.out.println("内容成功写入文件！");
        } catch (IOException e) {
            System.out.println("写入文件时出现错误：" + e.getMessage());

        }

    }
}

