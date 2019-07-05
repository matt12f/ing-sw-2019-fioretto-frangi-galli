 package it.polimi.se2019.model.cards;


 import java.io.Serializable;

 public class AmmoTileCard implements Serializable {

    private String content;
    /**
     *  constructor
     */
    public AmmoTileCard(String cont) {
        this.content=cont;
    }

    public String getContent() {
        return content;
    }

    @Override
    public AmmoTileCard clone(){
        return new AmmoTileCard(this.content);
    }

}