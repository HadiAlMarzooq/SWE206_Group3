package com.example.notthefinalproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javafx.collections.ObservableListBase;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class FileReader {



    public static ArrayList<Competition> competitions;





    public static void getDataList() throws IOException {

        ArrayList<Competition> compList = new ArrayList<>();
        FileInputStream file = new FileInputStream(new File("src/main/java/com/example/notthefinalproject/Competitions Participations.xlsx"));
        Workbook workbook = new XSSFWorkbook(file);

        Iterator<Sheet> ii = workbook.iterator();
        int sheetNumber = 0;
        while(ii.hasNext()){
            XSSFSheet sheet = (XSSFSheet) ii.next();
            System.out.println(sheet.getRow(4).getPhysicalNumberOfCells());
            boolean team = sheet.getRow(4).getPhysicalNumberOfCells() == 7; //this line will check if it's team or not.
            if (team){
                System.out.println("They are a team\n\n\n");
            }else {
                System.out.println("single\n\n\n");
            }
            Iterator<Row> itr = sheet.iterator();
            //iterating over excel file
            String compName = (itr.next()).getCell(1).getStringCellValue();
            String compUrl =  itr.next().getCell(1).getStringCellValue();
            Cell dateCell = itr.next().getCell(1);
            if(dateCell.getCellType() == CellType.NUMERIC){
                Date compDate =  dateCell.getDateCellValue();
                System.out.println("jjj");
                LocalDate d = LocalDate.of(compDate.getYear()+1900, compDate.getMonth()+1 ,compDate.getDate());
                compList.add(new Competition(compName, d, compUrl, !team));
            }else{
                String compDate = dateCell.getStringCellValue();
                System.out.println(compDate);
                System.out.println("hi");
                String[] dateArray = compDate.split("-");

                LocalDate d = LocalDate.of(Integer.parseInt(dateArray[0]),Integer.parseInt(dateArray[1]),Integer.parseInt(dateArray[2]) );
                compList.add(new Competition(compName, d, compUrl, !team));

            }


            itr.next();
            double laseTeamAdded = 0;
            while (itr.hasNext())
            {
                Row row = itr.next();

                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                if(!team){
                    cellIterator.next();
                    Cell id =  cellIterator.next();
                    double pId = id.getCellType() == CellType.NUMERIC ? id.getNumericCellValue() : Double.parseDouble(id.getStringCellValue());
                    String pName = cellIterator.next().getStringCellValue();
                    String pMajor = cellIterator.next().getStringCellValue();
                    Cell rankc =  cellIterator.next();
                    String rank = rankc.getCellType() == CellType.STRING ? rankc.getStringCellValue() : rankc.getNumericCellValue()+" ";
                    compList.get(compList.size()-1).addPartecipant(new SinglePartecipant(new Student(
                            pName,
                            pId,
                            pMajor
                    ), rank));


                }
                else{
                    cellIterator.next();
                    double pId = cellIterator.next().getNumericCellValue();
                    String pName = cellIterator.next().getStringCellValue();
                    String pMajor = cellIterator.next().getStringCellValue();
                    double teamNumber = cellIterator.next().getNumericCellValue();
                    String teamName = cellIterator.next().getStringCellValue();

                    Cell rankc =  cellIterator.next();
                    String rank = rankc.getCellType() == CellType.STRING ? rankc.getStringCellValue() : rankc.getNumericCellValue()+" ";
                    Student st = new Student(pName, pId, pMajor);
                    if(teamNumber == laseTeamAdded){
                        ((TeamPartecipant) (compList.get(compList.size()-1).partecipants.get(compList.get(compList.size()-1).partecipants.size()-1))).addMumber(st);
                    }else{
                        TeamPartecipant tp = new TeamPartecipant(teamName, rank);
                        tp.addMumber(st);
                        compList.get(compList.size()-1).addPartecipant(tp);
                    }
                    laseTeamAdded = teamNumber;



                }
            }
        }
        competitions = compList;
    }

    public static void writeData() throws IOException, InvalidFormatException {


        Workbook workbook = new XSSFWorkbook();
        XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        for(Competition comp : competitions){
            Sheet sheet = workbook.createSheet(comp.compName);
            Row firstRow = sheet.createRow(0);
            Cell cell = firstRow.createCell(0);
            cell.setCellValue("Competition Name");
            cell = firstRow.createCell(1);
            cell.setCellValue(comp.compName);
            firstRow = sheet.createRow(1);
            cell = firstRow.createCell(0);
            cell.setCellValue("Competition Link");
            cell = firstRow.createCell(1);
            cell.setCellValue(comp.compUrl);
            firstRow = sheet.createRow(2);
            cell = firstRow.createCell(0);
            cell.setCellValue("competition date");
            cell = firstRow.createCell(1);

            cell.setCellValue(comp.compDate.toString());



            int i = 4;
            System.out.println(comp.single);
            if(comp.single){
                firstRow = sheet.createRow(i);
                cell = firstRow.createCell(0);
                cell.setCellStyle(style);
                cell = firstRow.createCell(1);
                cell.setCellStyle(style);
                cell.setCellValue("Student ID");
                cell = firstRow.createCell(2);
                cell.setCellStyle(style);
                cell.setCellValue("Student Name");
                cell = firstRow.createCell(3);
                cell.setCellStyle(style);
                cell.setCellValue("Major");
                cell = firstRow.createCell(4);
                cell.setCellStyle(style);
                cell.setCellValue("Rank");
                i++;
                int j = 1;
                for(Partecipant par : comp.partecipants){
                    System.out.println(((SinglePartecipant) par).partecipant.name);
                    firstRow = sheet.createRow(i);
                    cell = firstRow.createCell(0);
                    cell.setCellStyle(style);

                    cell.setCellValue(j);
                    cell = firstRow.createCell(1);
                    cell.setCellStyle(style);
                    cell.setCellValue(((SinglePartecipant) par).partecipant.id);
                    cell = firstRow.createCell(2);
                    cell.setCellStyle(style);
                    cell.setCellValue(((SinglePartecipant) par).partecipant.name);
                    cell = firstRow.createCell(3);
                    cell.setCellStyle(style);
                    cell.setCellValue(((SinglePartecipant) par).partecipant.major);
                    cell = firstRow.createCell(4);
                    cell.setCellStyle(style);
                    cell.setCellValue(par.rank);
                    j++;
                    i++;



                }
                sheet.autoSizeColumn(0);
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.autoSizeColumn(3);
                sheet.autoSizeColumn(4);
                sheet.autoSizeColumn(5);

            }else{
                firstRow = sheet.createRow(i);
                cell = firstRow.createCell(0);
                cell.setCellStyle(style);
                cell = firstRow.createCell(1);
                cell.setCellStyle(style);

                cell.setCellValue("Student ID");
                cell = firstRow.createCell(2);
                cell.setCellStyle(style);
                cell.setCellValue("Student Name");
                cell = firstRow.createCell(3);
                cell.setCellStyle(style);
                cell.setCellValue("Major");
                cell = firstRow.createCell(4);
                cell.setCellStyle(style);
                cell.setCellValue("team");
                cell = firstRow.createCell(5);
                cell.setCellStyle(style);
                cell.setCellValue("Team Name");
                cell = firstRow.createCell(6);
                cell.setCellStyle(style);
                cell.setCellValue("Rank");
                i++;
                int teamNumber = 1;
                int j = 1;
                for(Partecipant par : comp.partecipants){
                    TeamPartecipant team = (TeamPartecipant) par;
                    for(Student member : team.teamMumbers){
                        firstRow = sheet.createRow(i);
                        cell = firstRow.createCell(0);
                        cell.setCellStyle(style);


                        cell.setCellValue(j);
                        cell = firstRow.createCell(1);
                        cell.setCellStyle(style);
                        cell.setCellValue(member.id);
                        cell = firstRow.createCell(2);
                        cell.setCellStyle(style);
                        cell.setCellValue(member.name);
                        cell = firstRow.createCell(3);
                        cell.setCellStyle(style);
                        cell.setCellValue(member.major);
                        cell = firstRow.createCell(4);
                        cell.setCellStyle(style);
                        cell.setCellValue(teamNumber);
                        cell = firstRow.createCell(5);
                        cell.setCellStyle(style);
                        cell.setCellValue(team.teamName);
                        cell = firstRow.createCell(6);
                        cell.setCellStyle(style);
                        cell.setCellValue(team.rank);
                        i++;
                        j++;
                    }

                   teamNumber++;



                }
                sheet.autoSizeColumn(0);
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.autoSizeColumn(3);
                sheet.autoSizeColumn(4);
                sheet.autoSizeColumn(5);
            }



        }
        FileOutputStream file = new FileOutputStream(new File("src/main/java/com/example/notthefinalproject/Competitions Participations.xlsx"));
        workbook.write(file);
        file.close();
        workbook.close();
    }


}
