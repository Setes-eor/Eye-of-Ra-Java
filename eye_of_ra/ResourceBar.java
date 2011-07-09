/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eye_of_ra;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setes
 */
public class ResourceBar extends Entity{
    
    // attributes of the class
    //
    BasicEntity be_inhabs;
    BasicEntity be_numbers;
    List<BasicEntity> lbe_Money;
    List<BasicEntity> lbe_Wood;
    List<BasicEntity> lbe_Brick;
    List<BasicEntity> lbe_Inhabs;
    int PanelElementCounter; 
    int m_fCoAnf; 
    int m_iCoWo; 
    int m_iCoBr; 
    int m_iCoIn; 
    int m_iMoneyX; 
    int m_iRY; 
    int m_iInhabsX;
    int m_iInhabsY;
    int m_iWoodX, m_iWoodY;
    int m_iBrickX, m_iBrickY;
    int m_iSMoney;
    
    //constructor
    //
    public ResourceBar(String ref, String ref2, String data, String typ, int x, int y){
        super(ref,x,y);
    
        be_inhabs = new BasicEntity(ref2, x, y + 45);
        m_iSMoney = 4;
        m_iMoneyX = 130;
        m_iInhabsX = 130;
        m_iInhabsY = 59;
        m_iWoodX = 280;
        m_iBrickX = 430;
        m_iRY = 14;
        
        lbe_Money = new ArrayList<BasicEntity>();
        lbe_Wood = new ArrayList<BasicEntity>();
        lbe_Brick = new ArrayList<BasicEntity>();
        lbe_Inhabs = new ArrayList<BasicEntity>();
        
        for (int i = 0; i < m_iSMoney; i++) {
            
            BasicEntity money = new BasicEntity(data + "Anzeigen/" + "Zahlen" + typ, m_iMoneyX, m_iRY);
            BasicEntity wood = new BasicEntity(data + "Anzeigen/" + "Zahlen" + typ, m_iWoodX, m_iRY);
            BasicEntity brick = new BasicEntity(data + "Anzeigen/" + "Zahlen" + typ, m_iBrickX, m_iRY);
            BasicEntity inhabs = new BasicEntity(data + "Anzeigen/" + "Zahlen" + typ, m_iInhabsX, m_iInhabsY);
            
            lbe_Money.add(money); 
            lbe_Wood.add(wood);
            lbe_Brick.add(brick);
            lbe_Inhabs.add(inhabs);
            
            m_iMoneyX -= 20;
            m_iWoodX -= 20;
            m_iBrickX -= 20;
            m_iInhabsX -= 20;
        }// for
    }//  constructor

    public void Draw(Graphics g, int money, int wood, int brick, int inhabs){
        sp_sprite.Draw(g,(int) d_xPos,(int) d_yPos);
        be_inhabs.Draw(g);
        
        for (int i = 0; i < lbe_Money.size(); i++) {

            switch (i) {

                case (0):
                    m_fCoAnf = money % 10;
                    m_iCoWo = wood % 10;
                    m_iCoBr = brick % 10;
                    m_iCoIn = inhabs % 10;
                    break;
                case (1):
                    m_fCoAnf = (money % 100) / 10;
                    m_iCoWo = (wood % 100) / 10;
                    m_iCoBr = (brick % 100) / 10;
                    m_iCoIn = (inhabs % 100) / 10;
                    break;
                case (2):
                    m_fCoAnf = (money % 1000) / 100;
                    m_iCoWo = (wood % 1000) / 100;
                    m_iCoBr = (brick % 1000) / 100;
                    m_iCoIn = (inhabs % 1000) / 100;
                    break;
                case (3):
                    m_fCoAnf = (money % 10000) / 1000;
                    m_iCoWo = (wood % 10000) / 1000;
                    m_iCoBr = (brick % 10000) / 1000;
                    m_iCoIn = (inhabs % 10000) / 1000;
                    break;
                default:
                    break;
            }// swich

            lbe_Money.get(i).sp_sprite.setSubImage(0, 0, 20, 28);
            lbe_Wood.get(i).sp_sprite.setSubImage(0, 0, 20, 28);
            lbe_Brick.get(i).sp_sprite.setSubImage(0, 0, 20, 28);
            lbe_Inhabs.get(i).sp_sprite.setSubImage(0, 0, 20, 28);
            
            lbe_Money.get(i).Draw(g);
            lbe_Wood.get(i).Draw(g);
            lbe_Brick.get(i).Draw(g);
            lbe_Inhabs.get(i).Draw(g);
        }// for
    }// Draw
    
    @Override
    public void collionWidth(Entity e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}// class ResourceBar
