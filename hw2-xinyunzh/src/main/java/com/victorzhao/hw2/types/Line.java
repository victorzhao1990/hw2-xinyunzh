

/* First created by JCasGen Sun Oct 05 19:21:28 EDT 2014 */
package com.victorzhao.hw2.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sun Oct 05 21:27:58 EDT 2014
 * XML source: /Users/victorzhao/git/hw2-xinyunzh/hw2-xinyunzh/src/main/resources/descriptors/hw2TypeSystemDescriptor.xml
 * @generated */
public class Line extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Line.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Line() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Line(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Line(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Line(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: Content

  /** getter for Content - gets 
   * @generated
   * @return value of the feature 
   */
  public String getContent() {
    if (Line_Type.featOkTst && ((Line_Type)jcasType).casFeat_Content == null)
      jcasType.jcas.throwFeatMissing("Content", "com.victorzhao.hw2.types.Line");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Line_Type)jcasType).casFeatCode_Content);}
    
  /** setter for Content - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setContent(String v) {
    if (Line_Type.featOkTst && ((Line_Type)jcasType).casFeat_Content == null)
      jcasType.jcas.throwFeatMissing("Content", "com.victorzhao.hw2.types.Line");
    jcasType.ll_cas.ll_setStringValue(addr, ((Line_Type)jcasType).casFeatCode_Content, v);}    
   
    
  //*--------------*
  //* Feature: SentenceId

  /** getter for SentenceId - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSentenceId() {
    if (Line_Type.featOkTst && ((Line_Type)jcasType).casFeat_SentenceId == null)
      jcasType.jcas.throwFeatMissing("SentenceId", "com.victorzhao.hw2.types.Line");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Line_Type)jcasType).casFeatCode_SentenceId);}
    
  /** setter for SentenceId - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentenceId(String v) {
    if (Line_Type.featOkTst && ((Line_Type)jcasType).casFeat_SentenceId == null)
      jcasType.jcas.throwFeatMissing("SentenceId", "com.victorzhao.hw2.types.Line");
    jcasType.ll_cas.ll_setStringValue(addr, ((Line_Type)jcasType).casFeatCode_SentenceId, v);}    
  }

    