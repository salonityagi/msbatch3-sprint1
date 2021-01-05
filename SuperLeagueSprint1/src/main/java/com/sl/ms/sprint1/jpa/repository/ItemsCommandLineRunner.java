package com.sl.ms.sprint1.jpa.repository;

import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;

@Component
public class ItemsCommandLineRunner implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(ItemsCommandLineRunner.class);
	public static final String SAMPLE_XLSX_FILE_PATH = "D:\\Saloni\\data\\sample-xls-file.xls";
	
	@Autowired
	private ItemsRepository repo;

	@Override
	public void run(String... args) throws Exception {
		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

		// Retrieving the number of sheets in the Workbook
		System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

		//use a Java 8 forEach with lambda
		workbook.forEach(sheet -> {
			System.out.println("=> " + sheet.getSheetName());
		});

		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(0);

		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// for-each loop to iterate over the rows and columns
		System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
		//Clean repo
		repo.deleteAll();
		for (Row row: sheet) {
			if(row.getRowNum() == 0){
				System.out.println("Ignoring...header, row number " + row.getRowNum());
			} else {
				System.out.print("Processing row: " + row.getRowNum());
				Long id = 0l;
				Integer price = 1;
				Integer quantity = 1;
				try{
					id = Long.parseLong(dataFormatter.formatCellValue(row.getCell(0)));
				} catch (Exception exception) {
					continue;
				}
				try{
					price = Integer.parseInt(dataFormatter.formatCellValue(row.getCell(2)));
				} catch (Exception exception) {

				}
				try{
					quantity = Integer.parseInt(dataFormatter.formatCellValue(row.getCell(3)));
				} catch (Exception exception) {

				}

				repo.save(new Items(id, dataFormatter.formatCellValue(row.getCell(1)), price, quantity, new Date()));
			}
			System.out.println(repo);
		}
		// Closing the workbook
		workbook.close();

	}

}
