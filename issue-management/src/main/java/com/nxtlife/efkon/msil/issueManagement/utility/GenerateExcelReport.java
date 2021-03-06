package com.nxtlife.efkon.msil.issueManagement.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nxtlife.efkon.msil.issueManagement.entity.Incident;

public class GenerateExcelReport {

	public static ByteArrayInputStream incidentsToExcel(List<Incident> incidents) throws IOException {
		String[] COLUMNs = { "Incident Id", "Transporter Id", "Transporter Name", "Username","Issue Type" , "Incident Subject Line",
				"Incident Created Timestamp", "Incident Resolved Timestamp",  "Incident Remark", "Support Remark" , "Is Closed" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet("Incidents");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Header Row
			Row headerRow = sheet.createRow(0);

			// Table Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (Incident incident : incidents) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0)
						.setCellValue(incident.getIncidentID() == null ? " " : incident.getIncidentID().toString());
				row.createCell(1).setCellValue(incident.getTransporterID() == null ? " " : incident.getTransporterID());
				row.createCell(2)
						.setCellValue(incident.getTransporterName() == null ? " " : incident.getTransporterName());
				row.createCell(3).setCellValue(incident.getUsername() == null ? " " : incident.getUsername());
				row.createCell(4).setCellValue(incident.getIssueTypeStr() == null ? " " : incident.getIssueTypeStr());
				row.createCell(5).setCellValue(incident.getSubjectLine() == null ? " " : incident.getSubjectLine());
				
				row.createCell(6).setCellValue(
						incident.getReportDateTime() == null ? " " : incident.getReportDateTime().toString());
				row.createCell(7).setCellValue(
						incident.getResolvedDateTime() == null ? " " : incident.getResolvedDateTime().toString());

				
				row.createCell(8).setCellValue(incident.getRemarks() == null ? " " : incident.getRemarks());
				row.createCell(9)
						.setCellValue(incident.getSupportRemark() == null ? " " : incident.getSupportRemark());
				row.createCell(10)
				.setCellValue(incident.getIsClosed() == null ? " " : incident.getIsClosed().toString());


			}

			// Auto-size all the above columns

			for (int col = 0; col < COLUMNs.length; col++)
				sheet.autoSizeColumn(col);

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
}