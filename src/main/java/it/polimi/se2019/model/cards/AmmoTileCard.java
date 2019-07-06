 package it.polimi.se2019.model.cards;


 import java.io.Serializable;

 /**
  * This class represents the Ammo Tile Card and contains the content (ammo cubes and/or powerups) listed as
  * a string where each char represents either a colored ammo cube ('y','b','r') or a powerup ('p')
  */
 public class AmmoTileCard implements Serializable {

    private String content;

     /**
      * Simple constructor
      * @param cont is the ammotile content
      */
    public AmmoTileCard(String cont) {
        this.content=cont;
    }

     /**
      * Simple getter
      * @return ammo tile content
      */
    public String getContent() {
        return content;
    }

     /**
      * method for deep cloning
      * @return a deep cloned AmmoTileCard
      */
    @Override
    public AmmoTileCard clone(){
        return new AmmoTileCard(this.content);
    }

}