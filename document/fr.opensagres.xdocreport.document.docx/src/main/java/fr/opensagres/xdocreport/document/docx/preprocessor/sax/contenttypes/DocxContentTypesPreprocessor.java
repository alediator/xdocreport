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
package fr.opensagres.xdocreport.document.docx.preprocessor.sax.contenttypes;

import java.util.Map;

import fr.opensagres.xdocreport.document.preprocessor.IXDocPreprocessor;
import fr.opensagres.xdocreport.document.preprocessor.sax.BufferedDocumentContentHandler;
import fr.opensagres.xdocreport.document.preprocessor.sax.SAXXDocPreprocessor;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import fr.opensagres.xdocreport.template.formatter.IDocumentFormatter;

/**
 * SAX Processor which parses [Content_Types].xml to add missing image format. Ex :
 * 
 * <pre>
 * <Default Extension="jpg" ContentType="image/jpeg" />
 * </pre>
 */
public class DocxContentTypesPreprocessor
    extends SAXXDocPreprocessor
{

    public static final IXDocPreprocessor INSTANCE = new DocxContentTypesPreprocessor();

    @Override
    protected BufferedDocumentContentHandler createBufferedDocumentContentHandler( String entryName,
                                                                                   FieldsMetadata fieldsMetadata,
                                                                                   IDocumentFormatter formatter,
                                                                                   Map<String, Object> sharedContext )
    {
        return new DocxContentTypesDocumentContentHandler( entryName, fieldsMetadata, formatter, sharedContext );
    }

}
