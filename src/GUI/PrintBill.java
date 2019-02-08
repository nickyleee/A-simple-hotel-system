package GUI;

import java.io.FileNotFoundException;
  import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import model.Guest;

public class PrintBill {
    public void print(Guest guest, String roomType)
    {
        try{
            Document document = new Document();
            System.out.println();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(System.getProperty("user.dir")+"/src/resourse/invoice.pdf"));

            document.open();

            addHeader(writer);

            Paragraph paragraph = new Paragraph("\n\n\n\n\nInvoice Date: " + LocalDate.now());
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(paragraph);
            document.add(new Paragraph("Dear " + guest.getName()));//guest name

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            List<PdfPRow> listRow = table.getRows();
            float[] columnWidths = { 1f, 2f, 1f, 1f,1f,1f };
            table.setWidths(columnWidths);
            PdfPCell cells1[]= new PdfPCell[6];
            PdfPRow row1 = new PdfPRow(cells1);
            cells1[0] = new PdfPCell(new Paragraph("Room"));
            cells1[0].setPaddingLeft(20);
            cells1[0].setMinimumHeight(30);
            cells1[0].setHorizontalAlignment(Element.ALIGN_CENTER);
            cells1[0].setVerticalAlignment(Element.ALIGN_MIDDLE);
            cells1[1] = new PdfPCell(new Paragraph("Room Type"));
            cells1[1].setHorizontalAlignment(Element.ALIGN_CENTER);
            cells1[1].setVerticalAlignment(Element.ALIGN_MIDDLE);
            cells1[2] = new PdfPCell(new Paragraph("Check in"));
            cells1[2].setHorizontalAlignment(Element.ALIGN_CENTER);
            cells1[2].setVerticalAlignment(Element.ALIGN_MIDDLE);
            cells1[3] = new PdfPCell(new Paragraph("Check out"));
            cells1[3].setHorizontalAlignment(Element.ALIGN_CENTER);
            cells1[3].setVerticalAlignment(Element.ALIGN_MIDDLE);
            cells1[4] = new PdfPCell(new Paragraph("Days"));
            cells1[4].setHorizontalAlignment(Element.ALIGN_CENTER);
            cells1[4].setVerticalAlignment(Element.ALIGN_MIDDLE);
            cells1[5] = new PdfPCell(new Paragraph("Price/night"));
            cells1[5].setHorizontalAlignment(Element.ALIGN_CENTER);
            cells1[5].setVerticalAlignment(Element.ALIGN_MIDDLE);


            PdfPCell cells2[]= new PdfPCell[6];
            PdfPRow row2 = new PdfPRow(cells2);
            cells2[0] = new PdfPCell(new Paragraph("" + guest.getRoomNum()));
            cells2[0].setPaddingLeft(20);
            cells2[0].setMinimumHeight(300);
            cells2[0].setHorizontalAlignment(Element.ALIGN_CENTER);
            cells2[1] = new PdfPCell(new Paragraph(roomType));
            cells2[1].setHorizontalAlignment(Element.ALIGN_CENTER);
            cells2[2] = new PdfPCell(new Paragraph(guest.getCheck_In()));
            cells2[2].setHorizontalAlignment(Element.ALIGN_CENTER);
            cells2[3] = new PdfPCell(new Paragraph(guest.getCheck_Out()));
            cells2[3].setHorizontalAlignment(Element.ALIGN_CENTER);
            cells2[4] = new PdfPCell(new Paragraph(guest.getPayment().getNumber_days() + ""));
            cells2[4].setHorizontalAlignment(Element.ALIGN_CENTER);
            cells2[5] = new PdfPCell(new Paragraph(guest.getPayment().getRoom_Price()+""));
            cells2[5].setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell cells3[]= new PdfPCell[6];
            PdfPRow row3 = new PdfPRow(cells3);
            cells3[0] = new PdfPCell(new Paragraph(""));
            cells3[0].setBorderColor(BaseColor.WHITE);
            cells3[0].setPaddingLeft(20);
            cells3[0].setMinimumHeight(30);
            cells3[1] = new PdfPCell(new Paragraph(""));
            cells3[1].setBorderColor(BaseColor.WHITE);
            cells3[2] = new PdfPCell(new Paragraph(""));
            cells3[2].setBorderColor(BaseColor.WHITE);
            cells3[3] = new PdfPCell(new Paragraph(""));
            cells3[3].setBorderColor(BaseColor.WHITE);
            cells3[4] = new PdfPCell(new Paragraph("TOTAL"));
            cells3[4].setHorizontalAlignment(Element.ALIGN_CENTER);
            cells3[4].setVerticalAlignment(Element.ALIGN_MIDDLE);
            cells3[5] = new PdfPCell(new Paragraph(""+guest.getPayment().getTotal_Cost()));

            PdfPCell cells4[]= new PdfPCell[6];
            PdfPRow row4 = new PdfPRow(cells4);
            cells4[0] = new PdfPCell(new Paragraph(""));
            cells4[0].setBorderColor(BaseColor.WHITE);
            cells4[0].setPaddingLeft(20);
            cells4[0].setMinimumHeight(30);
            cells4[1] = new PdfPCell(new Paragraph(""));
            cells4[1].setBorderColor(BaseColor.WHITE);
            cells4[2] = new PdfPCell(new Paragraph(""));
            cells4[2].setBorderColor(BaseColor.WHITE);
            cells4[3] = new PdfPCell(new Paragraph(""));
            cells4[3].setBorderColor(BaseColor.WHITE);
            cells4[4] = new PdfPCell(new Paragraph("EXTRA FEE"));
            cells4[4].setHorizontalAlignment(Element.ALIGN_CENTER);
            cells4[4].setVerticalAlignment(Element.ALIGN_MIDDLE);
            cells4[5] = new PdfPCell(new Paragraph(""+guest.getPayment().getExtra_fee()));

            listRow.add(row1);
            listRow.add(row2);
            listRow.add(row4);
            listRow.add(row3);

            document.add(table);
            document.add(new Paragraph("We have received a"+guest.getPayment().getPayment_Type()+ "payment for the invoice amount" ));

            document.close();
            writer.close();
        }catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (DocumentException e)
        {
            e.printStackTrace();

        }
    }

    private void addHeader(PdfWriter writer) {
        PdfPTable header = new PdfPTable(2);
        try {
            header.setWidths(new int[]{2, 24});
            header.setTotalWidth(527);
            header.setLockedWidth(true);
            header.getDefaultCell().setFixedHeight(40);
            header.getDefaultCell().setBorder(Rectangle.BOTTOM);
            header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            Image logo = Image.getInstance(System.getProperty("user.dir")+"/src/resourse/logo.png");
            header.addCell(logo);

            PdfPCell text = new PdfPCell();
            text.setPaddingBottom(15);
            text.setPaddingLeft(10);
            text.setBorder(Rectangle.BOTTOM);
            text.setBorderColor(BaseColor.LIGHT_GRAY);
            text.addElement(new Phrase("LINNAEUS HOTEL", new Font(Font.FontFamily.HELVETICA, 28)));
            header.addCell(text);

            header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        } catch (MalformedURLException e) {
            throw new ExceptionConverter(e);
        } catch (IOException e) {
            throw new ExceptionConverter(e);
        }
    }
}
