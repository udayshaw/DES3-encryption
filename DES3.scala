import org.apache.commons.codec.binary.Base64
import crypto._
import protocol.defaults._
  
object DES3 extends App {

  def encodeBase64(bytes: Array[Byte]) = Base64.encodeBase64String(bytes)
  def encryptKey(key : String): String = {
    val key1=encodeBase64(DES.encrypt(key, "SPARK1"))
    val key2=encodeBase64(DES.encrypt(key1, "SPARK2"))
    encodeBase64(DES.encrypt(key2, "SPARK3"))
  }

  def decryptKey(key : String): String = {
   val key1=new String(DES.decrypt(Base64.decodeBase64(key.getBytes("UTF-8")),"SPARK3"))
   val key2=new String(DES.decrypt(Base64.decodeBase64(key1.getBytes("UTF-8")),"SPARK2"))
   new String(DES.decrypt(Base64.decodeBase64(key2.getBytes("UTF-8")),"SPARK1"))
  }

}
