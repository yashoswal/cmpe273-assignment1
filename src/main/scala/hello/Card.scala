package hello
import javax.validation.constraints.NotNull

class Card(val cardna : String, val card_number : String, val exp_date : String){
  
  private var Id = "p"
  @NotNull
  private var cardname = cardna
  @NotNull
  private var cardnumber = card_number
  private var expdate = exp_date
  
   def this()={
    this(null,null,null)
  }
  
  
  def setcardId(cardId : String) : Unit = {Id = cardId  }
  def setcardNumber(cardNumber : String) : Unit = {cardnumber = cardNumber  }
  //def setcardname : Unit ={card_name = cardname}
  def getcardId : String = {return Id}
  def getcardnumber : String = {return cardnumber}
  def getexpdate : String = {return expdate  }
  def getcardname : String = {return cardname }

}