package cn.liangjiateng.thrift_client.job;

/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2018-06-27")
public class JobServiceException extends org.apache.thrift.TException implements org.apache.thrift.TBase<JobServiceException, JobServiceException._Fields>, java.io.Serializable, Cloneable, Comparable<JobServiceException> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("cn.liangjiateng.thrift_client.job.JobServiceException");

  private static final org.apache.thrift.protocol.TField CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("code", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField MSG_FIELD_DESC = new org.apache.thrift.protocol.TField("msg", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("time", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField DETAIL_FIELD_DESC = new org.apache.thrift.protocol.TField("detail", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new JobServiceExceptionStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new JobServiceExceptionTupleSchemeFactory();

  public int code; // required
  public java.lang.String msg; // required
  public java.lang.String time; // required
  public java.lang.String detail; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CODE((short)1, "code"),
    MSG((short)2, "msg"),
    TIME((short)3, "time"),
    DETAIL((short)4, "detail");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // CODE
          return CODE;
        case 2: // MSG
          return MSG;
        case 3: // TIME
          return TIME;
        case 4: // DETAIL
          return DETAIL;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __CODE_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CODE, new org.apache.thrift.meta_data.FieldMetaData("code", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.MSG, new org.apache.thrift.meta_data.FieldMetaData("msg", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TIME, new org.apache.thrift.meta_data.FieldMetaData("time", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DETAIL, new org.apache.thrift.meta_data.FieldMetaData("detail", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(JobServiceException.class, metaDataMap);
  }

  public JobServiceException() {
  }

  public JobServiceException(
    int code,
    java.lang.String msg,
    java.lang.String time,
    java.lang.String detail)
  {
    this();
    this.code = code;
    setCodeIsSet(true);
    this.msg = msg;
    this.time = time;
    this.detail = detail;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public JobServiceException(JobServiceException other) {
    __isset_bitfield = other.__isset_bitfield;
    this.code = other.code;
    if (other.isSetMsg()) {
      this.msg = other.msg;
    }
    if (other.isSetTime()) {
      this.time = other.time;
    }
    if (other.isSetDetail()) {
      this.detail = other.detail;
    }
  }

  public JobServiceException deepCopy() {
    return new JobServiceException(this);
  }

  @Override
  public void clear() {
    setCodeIsSet(false);
    this.code = 0;
    this.msg = null;
    this.time = null;
    this.detail = null;
  }

  public int getCode() {
    return this.code;
  }

  public JobServiceException setCode(int code) {
    this.code = code;
    setCodeIsSet(true);
    return this;
  }

  public void unsetCode() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __CODE_ISSET_ID);
  }

  /** Returns true if field code is set (has been assigned a value) and false otherwise */
  public boolean isSetCode() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __CODE_ISSET_ID);
  }

  public void setCodeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __CODE_ISSET_ID, value);
  }

  public java.lang.String getMsg() {
    return this.msg;
  }

  public JobServiceException setMsg(java.lang.String msg) {
    this.msg = msg;
    return this;
  }

  public void unsetMsg() {
    this.msg = null;
  }

  /** Returns true if field msg is set (has been assigned a value) and false otherwise */
  public boolean isSetMsg() {
    return this.msg != null;
  }

  public void setMsgIsSet(boolean value) {
    if (!value) {
      this.msg = null;
    }
  }

  public java.lang.String getTime() {
    return this.time;
  }

  public JobServiceException setTime(java.lang.String time) {
    this.time = time;
    return this;
  }

  public void unsetTime() {
    this.time = null;
  }

  /** Returns true if field time is set (has been assigned a value) and false otherwise */
  public boolean isSetTime() {
    return this.time != null;
  }

  public void setTimeIsSet(boolean value) {
    if (!value) {
      this.time = null;
    }
  }

  public java.lang.String getDetail() {
    return this.detail;
  }

  public JobServiceException setDetail(java.lang.String detail) {
    this.detail = detail;
    return this;
  }

  public void unsetDetail() {
    this.detail = null;
  }

  /** Returns true if field detail is set (has been assigned a value) and false otherwise */
  public boolean isSetDetail() {
    return this.detail != null;
  }

  public void setDetailIsSet(boolean value) {
    if (!value) {
      this.detail = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case CODE:
      if (value == null) {
        unsetCode();
      } else {
        setCode((java.lang.Integer)value);
      }
      break;

    case MSG:
      if (value == null) {
        unsetMsg();
      } else {
        setMsg((java.lang.String)value);
      }
      break;

    case TIME:
      if (value == null) {
        unsetTime();
      } else {
        setTime((java.lang.String)value);
      }
      break;

    case DETAIL:
      if (value == null) {
        unsetDetail();
      } else {
        setDetail((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case CODE:
      return getCode();

    case MSG:
      return getMsg();

    case TIME:
      return getTime();

    case DETAIL:
      return getDetail();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case CODE:
      return isSetCode();
    case MSG:
      return isSetMsg();
    case TIME:
      return isSetTime();
    case DETAIL:
      return isSetDetail();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof JobServiceException)
      return this.equals((JobServiceException)that);
    return false;
  }

  public boolean equals(JobServiceException that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_code = true;
    boolean that_present_code = true;
    if (this_present_code || that_present_code) {
      if (!(this_present_code && that_present_code))
        return false;
      if (this.code != that.code)
        return false;
    }

    boolean this_present_msg = true && this.isSetMsg();
    boolean that_present_msg = true && that.isSetMsg();
    if (this_present_msg || that_present_msg) {
      if (!(this_present_msg && that_present_msg))
        return false;
      if (!this.msg.equals(that.msg))
        return false;
    }

    boolean this_present_time = true && this.isSetTime();
    boolean that_present_time = true && that.isSetTime();
    if (this_present_time || that_present_time) {
      if (!(this_present_time && that_present_time))
        return false;
      if (!this.time.equals(that.time))
        return false;
    }

    boolean this_present_detail = true && this.isSetDetail();
    boolean that_present_detail = true && that.isSetDetail();
    if (this_present_detail || that_present_detail) {
      if (!(this_present_detail && that_present_detail))
        return false;
      if (!this.detail.equals(that.detail))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + code;

    hashCode = hashCode * 8191 + ((isSetMsg()) ? 131071 : 524287);
    if (isSetMsg())
      hashCode = hashCode * 8191 + msg.hashCode();

    hashCode = hashCode * 8191 + ((isSetTime()) ? 131071 : 524287);
    if (isSetTime())
      hashCode = hashCode * 8191 + time.hashCode();

    hashCode = hashCode * 8191 + ((isSetDetail()) ? 131071 : 524287);
    if (isSetDetail())
      hashCode = hashCode * 8191 + detail.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(JobServiceException other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetCode()).compareTo(other.isSetCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.code, other.code);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetMsg()).compareTo(other.isSetMsg());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMsg()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.msg, other.msg);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTime()).compareTo(other.isSetTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.time, other.time);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDetail()).compareTo(other.isSetDetail());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDetail()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.detail, other.detail);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("cn.liangjiateng.thrift_client.job.JobServiceException(");
    boolean first = true;

    sb.append("code:");
    sb.append(this.code);
    first = false;
    if (!first) sb.append(", ");
    sb.append("msg:");
    if (this.msg == null) {
      sb.append("null");
    } else {
      sb.append(this.msg);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("time:");
    if (this.time == null) {
      sb.append("null");
    } else {
      sb.append(this.time);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("detail:");
    if (this.detail == null) {
      sb.append("null");
    } else {
      sb.append(this.detail);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class JobServiceExceptionStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public JobServiceExceptionStandardScheme getScheme() {
      return new JobServiceExceptionStandardScheme();
    }
  }

  private static class JobServiceExceptionStandardScheme extends org.apache.thrift.scheme.StandardScheme<JobServiceException> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, JobServiceException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.code = iprot.readI32();
              struct.setCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // MSG
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.msg = iprot.readString();
              struct.setMsgIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.time = iprot.readString();
              struct.setTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // DETAIL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.detail = iprot.readString();
              struct.setDetailIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, JobServiceException struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(CODE_FIELD_DESC);
      oprot.writeI32(struct.code);
      oprot.writeFieldEnd();
      if (struct.msg != null) {
        oprot.writeFieldBegin(MSG_FIELD_DESC);
        oprot.writeString(struct.msg);
        oprot.writeFieldEnd();
      }
      if (struct.time != null) {
        oprot.writeFieldBegin(TIME_FIELD_DESC);
        oprot.writeString(struct.time);
        oprot.writeFieldEnd();
      }
      if (struct.detail != null) {
        oprot.writeFieldBegin(DETAIL_FIELD_DESC);
        oprot.writeString(struct.detail);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class JobServiceExceptionTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public JobServiceExceptionTupleScheme getScheme() {
      return new JobServiceExceptionTupleScheme();
    }
  }

  private static class JobServiceExceptionTupleScheme extends org.apache.thrift.scheme.TupleScheme<JobServiceException> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, JobServiceException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetCode()) {
        optionals.set(0);
      }
      if (struct.isSetMsg()) {
        optionals.set(1);
      }
      if (struct.isSetTime()) {
        optionals.set(2);
      }
      if (struct.isSetDetail()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetCode()) {
        oprot.writeI32(struct.code);
      }
      if (struct.isSetMsg()) {
        oprot.writeString(struct.msg);
      }
      if (struct.isSetTime()) {
        oprot.writeString(struct.time);
      }
      if (struct.isSetDetail()) {
        oprot.writeString(struct.detail);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, JobServiceException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.code = iprot.readI32();
        struct.setCodeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.msg = iprot.readString();
        struct.setMsgIsSet(true);
      }
      if (incoming.get(2)) {
        struct.time = iprot.readString();
        struct.setTimeIsSet(true);
      }
      if (incoming.get(3)) {
        struct.detail = iprot.readString();
        struct.setDetailIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

