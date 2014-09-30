package hello
import javax.validation.constraints.NotNull
class Web(@NotNull val url : String, val login : String, val pass : String){
  
  private var Id = "p"
  @NotNull
  private var weburl = url
  @NotNull
  private var weblogin = login
  @NotNull
  private var webpassword = pass
  
   def this()={
    this(null, null, null)
  }
  
  
  def setwebId(webId : String) : Unit = {Id = webId  }
  //def setcardNumber(cardNumber : String) : Unit = {cardnumber = cardNumber  }
  //def setcardname : Unit ={card_name = cardname}
  def getwebId : String = {return Id}
  def getweburl : String = {return weburl}
  def getweblogin : String = {return weblogin  }
  def getwebpassword : String = {return webpassword }

}

