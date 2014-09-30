package hello
import javax.validation.constraints.NotNull
class Bank(val aname : String, val rnum : String, val anum : String){
  
  private var Id = "p"
  @NotNull
  private var accountname = aname
  @NotNull
  private var routingnumber = rnum
  @NotNull
  private var accountnumber = anum
  
   def this()={
    this(null,null,null)
  }
  
  
  def setbankId(cardId : String) : Unit = {Id = cardId  }
  //def setcardNumber(cardNumber : String) : Unit = {cardnumber = cardNumber  }
  //def setcardname : Unit ={card_name = cardname}
  def getcardId : String = {return Id}
  def getaccountname : String = {return accountname}
  def getroutingnumber : String = {return routingnumber  }
  def getaccountnumber : String = {return accountnumber }

}