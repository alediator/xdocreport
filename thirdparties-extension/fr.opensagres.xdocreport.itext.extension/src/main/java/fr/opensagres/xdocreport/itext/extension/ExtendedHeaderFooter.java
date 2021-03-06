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

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

/**
 * fixes for pdf conversion by Leszek Piotrowicz <leszekp@safe-mail.net>
 */
public class ExtendedHeaderFooter
    extends PdfPageEventHelper
{
    private final ExtendedDocument document;

    private MasterPage masterPage;

    public ExtendedHeaderFooter( ExtendedDocument document )
    {
        this.document = document;
    }

    /**
     * Adds a header/footer to every page
     * 
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(com.itextpdf.text.pdf.PdfWriter,
     *      com.itextpdf.text.Document)
     */
    public void onStartPage( PdfWriter writer, Document doc )
    {
        if ( masterPage == null )
        {
            masterPage = document.getDefaultMasterPage();
        }

        if ( masterPage != null )
        {
            // Master page is defined

            IMasterPageHeaderFooter header = masterPage.getHeader();
            IMasterPageHeaderFooter footer = masterPage.getFooter();

            // Add header
            if ( header != null )
            {
                // compute upper-left corner coordinates
                float x = document.getOriginMarginLeft();
                float y = document.getPageSize().getHeight() - document.getOriginMarginTop();

                header.writeSelectedRows( 0, -1, x, y, writer.getDirectContentUnder() );
            }

            // Add footer
            if ( footer != null )
            {
                // compute upper-left corner coordinates
                float x = document.getOriginMarginLeft();
                float y = document.getOriginMarginBottom() + footer.getTotalHeight();

                footer.writeSelectedRows( 0, -1, x, y, writer.getDirectContentUnder() );
            }
        }
    }

    public void setMasterPage( MasterPage masterPage )
    {
        IMasterPageHeaderFooter header = masterPage.getHeader();
        IMasterPageHeaderFooter footer = masterPage.getFooter();

        float marginLeft = document.getOriginMarginLeft();
        float marginRight = document.getOriginMarginRight();
        float marginTop = document.getOriginMarginTop();
        if ( header != null )
        {
            marginTop += header.getTotalHeight();
        }
        float marginBottom = document.getOriginMarginBottom();
        if ( footer != null )
        {
            marginBottom += footer.getTotalHeight();
        }
        document.setMargins( marginLeft, marginRight, marginTop, marginBottom );

        this.masterPage = masterPage;
    }
}
