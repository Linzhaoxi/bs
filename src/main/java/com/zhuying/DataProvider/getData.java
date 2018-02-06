package com.zhuying.DataProvider;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.String;

import java.util.ArrayList;
import java.util.List;

public class getData {

    //从excel文件获取测试数据
    public Object[][] getXlsData(String filePath, String fileName, String sheetName) throws IOException {
        //声明File对象，根据文件路径和文件名
        File file=new File(filePath+"\\"+fileName);
        //创建FileInputStream对象读取文件
        FileInputStream input=new FileInputStream(file);
        Workbook workbook=null;
        Cell cell=null;

        //获取文件后缀（.xlsx还是.xls）
        String fileExtensionName=fileName.substring(fileName.indexOf("."));
        if (fileExtensionName.equals(".xlsx")) {
            workbook=new XSSFWorkbook(input);

        }else if (fileExtensionName.equals(".xls")) {
            workbook=new HSSFWorkbook(input);

        }

        //通过sheetName参数，获取sheet
        Sheet sheet=workbook.getSheet(sheetName);
        //获取某sheet总行数，最后一行和第一行相减，excel文件的行号和列号从0开始
        int row=sheet.getLastRowNum()-sheet.getFirstRowNum();

        //创建list对象，存储从excel文件读取的数据
        List<Object[]> records=new ArrayList<Object[]>();
        //循环遍历excel数据文件的所有数据，除了第一行，第一行为属性名称
        for (int i = 1; i < row+1; i++) {

            //获取行对象
            Row rowValue=sheet.getRow(i);
             //声明数组，用来存储N列数据，数组大小用getlastcellnum获取
            String[] fileds=new String[rowValue.getLastCellNum()];

            //遍历每一列
            for (int j = 0; j < rowValue.getLastCellNum(); j++) {

                //获取同一行的每一列值
                cell= rowValue.getCell(j);
                //若值为空，还需创建一个单元格
                if(cell==null){
                    cell = rowValue.createCell(j);
                }
                //设置单元格类型均为String
                cell.setCellType(CellType.STRING);
                //将每一个值，存储在fileds数组里
                fileds[j]=cell.getStringCellValue();
            }
            //将每一行数据（数组），添加到records列表里。
            records.add(fileds);
        }

        //将存储数据的List转换为一个object的二维数组
        Object[][] results=new Object[records.size()][];
        //设置二维数组每行的值，每行是一个object对象
        for (int i = 0; i < records.size(); i++) {
            results[i]=records.get(i);
        }

        return results;
    }

    //从csv文件获取测试数据
    public Object[][] getCsvDate(String filePath)throws IOException{
        //创建list对象，存储从csv文件读取的数据
        List<Object[]> records = new ArrayList<Object[]>();
        String record;

        //设定UTF-8字符集，使用带缓冲区的字符输入流BufferedReader读取文件内容
        BufferedReader file=new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
        //忽略读取CSV文件的标题行（第一行）
        file.readLine();
        //遍历读取文件中除第一行外的其他所有内容并存储在名为records的ArrayList中，每一行records中存储的对象为一个String数组
        while((record=file.readLine())!=null){
            String fields[]=record.split(",");
            records.add(fields);
        }
        //关闭文件对象
        file.close();
        //将存储测试数据的List转换为一个Object的二维数组
        Object[][] results=new Object[records.size()][];
        //设置二位数组每行的值，每行是一个Object对象
        for(int i=0;i<records.size();i++){
            results[i]=records.get(i);
        }
        return results;
    }


}
