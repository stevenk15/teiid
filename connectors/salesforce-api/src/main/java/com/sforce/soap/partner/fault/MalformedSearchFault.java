
package com.sforce.soap.partner.fault;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MalformedSearchFault complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MalformedSearchFault">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:fault.partner.soap.sforce.com}ApiQueryFault">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MalformedSearchFault")
public class MalformedSearchFault
    extends ApiQueryFault
{


}
