package fr.opensagres.xdocreport.remoting.resources.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PropertyRepresentation
{

    private String name;

    private String value;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue( String value )
    {
        this.value = value;
    }

}
