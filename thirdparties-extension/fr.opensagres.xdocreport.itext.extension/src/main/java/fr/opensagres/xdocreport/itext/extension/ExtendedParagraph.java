/**
 * Copyright (C) 2011 Angelo Zerr <angelo.zerr@gmail.com> and Pascal Leclercq <pascal.leclercq@gmail.com>
 *
 * All rights reserved.
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package fr.opensagres.xdocreport.itext.extension;

import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class ExtendedParagraph
    extends Paragraph
    implements IITextContainer
{

    private PdfPTable table;

    private PdfPCell cell;

    private IITextContainer container;

    public ExtendedParagraph()
    {
    }

    public ExtendedParagraph( Paragraph paragraph )
    {
        super( paragraph );
    }

    public void addElement( Element element )
    {
        super.add( element );
    }

    public Element getContainer()
    {
        if ( cell != null )
        {
            if ( table == null )
            {
                table = new PdfPTable( 1 );
                table.setWidthPercentage( 100f );
                cell.addElement( this );
                table.addCell( cell );
            }
            return table;
        }
        return this;
    }

    public PdfPCell getPdfPCell()
    {
        if ( cell != null )
        {
            return cell;
        }
        cell = createPdfPCell();
        return cell;
    }

    private synchronized PdfPCell createPdfPCell()
    {
        if ( cell != null )
        {
            return cell;
        }
        PdfPCell cell = new PdfPCell();
        cell.setBorder( Rectangle.NO_BORDER );
        // cell.setPadding(0);
        return cell;
    }

    public IITextContainer getITextContainer()
    {
        return container;
    }

    public void setITextContainer( IITextContainer container )
    {
        this.container = container;
    }
}
