package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity;

import java.io.Serializable;

/**
 * 短信接口需要用到的实体类
 * @author ZDW
 *
 */
public class MessageBean implements Serializable {

	private static final long serialVersionUID = -4813681244549677182L;

	private java.lang.String applicationid;

    private java.lang.String content;

    private java.lang.String phones;

    private java.lang.String reqdeliveryreport;

    private java.lang.String smID;

    public MessageBean() {
    }

    public MessageBean(
           java.lang.String applicationid,
           java.lang.String content,
           java.lang.String phones,
           java.lang.String reqdeliveryreport,
           java.lang.String smID) {
           this.applicationid = applicationid;
           this.content = content;
           this.phones = phones;
           this.reqdeliveryreport = reqdeliveryreport;
           this.smID = smID;
    }


    /**
     * Gets the applicationid value for this MessageBean.
     * 
     * @return applicationid
     */
    public java.lang.String getApplicationid() {
        return applicationid;
    }


    /**
     * Sets the applicationid value for this MessageBean.
     * 
     * @param applicationid
     */
    public void setApplicationid(java.lang.String applicationid) {
        this.applicationid = applicationid;
    }


    /**
     * Gets the content value for this MessageBean.
     * 
     * @return content
     */
    public java.lang.String getContent() {
        return content;
    }


    /**
     * Sets the content value for this MessageBean.
     * 
     * @param content
     */
    public void setContent(java.lang.String content) {
        this.content = content;
    }


    /**
     * Gets the phones value for this MessageBean.
     * 
     * @return phones
     */
    public java.lang.String getPhones() {
        return phones;
    }


    /**
     * Sets the phones value for this MessageBean.
     * 
     * @param phones
     */
    public void setPhones(java.lang.String phones) {
        this.phones = phones;
    }


    /**
     * Gets the reqdeliveryreport value for this MessageBean.
     * 
     * @return reqdeliveryreport
     */
    public java.lang.String getReqdeliveryreport() {
        return reqdeliveryreport;
    }


    /**
     * Sets the reqdeliveryreport value for this MessageBean.
     * 
     * @param reqdeliveryreport
     */
    public void setReqdeliveryreport(java.lang.String reqdeliveryreport) {
        this.reqdeliveryreport = reqdeliveryreport;
    }


    /**
     * Gets the smID value for this MessageBean.
     * 
     * @return smID
     */
    public java.lang.String getSmID() {
        return smID;
    }


    /**
     * Sets the smID value for this MessageBean.
     * 
     * @param smID
     */
    public void setSmID(java.lang.String smID) {
        this.smID = smID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MessageBean)) return false;
        MessageBean other = (MessageBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.applicationid==null && other.getApplicationid()==null) || 
             (this.applicationid!=null &&
              this.applicationid.equals(other.getApplicationid()))) &&
            ((this.content==null && other.getContent()==null) || 
             (this.content!=null &&
              this.content.equals(other.getContent()))) &&
            ((this.phones==null && other.getPhones()==null) || 
             (this.phones!=null &&
              this.phones.equals(other.getPhones()))) &&
            ((this.reqdeliveryreport==null && other.getReqdeliveryreport()==null) || 
             (this.reqdeliveryreport!=null &&
              this.reqdeliveryreport.equals(other.getReqdeliveryreport()))) &&
            ((this.smID==null && other.getSmID()==null) || 
             (this.smID!=null &&
              this.smID.equals(other.getSmID())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getApplicationid() != null) {
            _hashCode += getApplicationid().hashCode();
        }
        if (getContent() != null) {
            _hashCode += getContent().hashCode();
        }
        if (getPhones() != null) {
            _hashCode += getPhones().hashCode();
        }
        if (getReqdeliveryreport() != null) {
            _hashCode += getReqdeliveryreport().hashCode();
        }
        if (getSmID() != null) {
            _hashCode += getSmID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(MessageBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://services.webservices.platform.note.chinacreator.com/", "messageBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        /*elemField.setNillable(false);*/
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("content");
        elemField.setXmlName(new javax.xml.namespace.QName("", "content"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        /*elemField.setNillable(false);*/
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phones");
        elemField.setXmlName(new javax.xml.namespace.QName("", "phones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        /*elemField.setNillable(false);*/
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reqdeliveryreport");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reqdeliveryreport"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        /*elemField.setNillable(false);*/
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("smID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "smID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        /*elemField.setNillable(false);*/
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return new  org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return new  org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
    }

}
