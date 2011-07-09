/*
 * the resources of a player and ais
 * 
 */
package eye_of_ra;

/**
 *
 * @author setes
 */
public class Resources {
    
    // constructor
    //
    public Resources(String ID){
        l_oldtime = System.currentTimeMillis();
        l_oldtime2 = System.currentTimeMillis();
        l_oldtime3 = System.currentTimeMillis();
        s_playerID = ID;
    }// constructor
    
    // init the start resources of a player
    //
    public void InitResources(int money,  int inhabs1,int inhabs2,  int wood,  
            int brick, int slime,  int tools,  int fish, int milk,  int stone){
        
        m_iMoney = money;
        m_iInhabitans_1 = inhabs1;
        m_iInhabitans_2 = inhabs2;
        m_iWood = wood;
        m_iBrick = brick;
        m_iSlime = slime;
        m_iTools = tools;
        m_iFishes = fish;
        m_iMilk = milk;
        m_iStones = stone;
    }// InitResources
        
    // enough resources to build a building ?
    //
    public boolean EnaughResources(int Money, int Wood, int Brick, int Stones){

           if (m_iMoney - Money >= 0 && m_iWood - Wood >= 0
                && m_iBrick - Brick >= 0 && m_iStones - Stones >= 0) {
            return true;
        }// if
        return false;
    }// EnaughRessourc
        
       
    // pay the money and wood what ever for your buildings
    //
    public void costs(int Money, int Wood, int Brick, int Stones) {
        m_iMoney -= Money;
        m_iWood -= Wood;
        m_iBrick -= Brick;
        m_iStones -= Stones;

    }// Costs

    // set the factors to count the resources
    //
    public void setFactor(int factorM, int factorP/* ,Buildtyp name*/) {
        
        m_iMoneyFactor += factorM;
        
        /*switch (name) {
            case (house):
                m_iApartments_1 += factorP;
                m_iInhabitans_1 += 2 * factorP;
                break;
            case (lumberjack):
                m_iWoodFactor += factorP;
                break;
            case (slimegrave):
                m_iSlimeFactor += factorP;
                break;
            case (brickmaker):
                m_iBrickFactor += factorP;
                break;
            case (fisher):
                m_iFishFactor += factorP;
                break;
            case (milkfarm):
                m_iMilkFactor += factorP;
                break;
            default:
                break;*/
    }// setFactor
        
    // count the resources
    //
    public void countResources() {

        // Wurde das erste mal Milch prodzuiert wird die max Anzahl von Bewohner
        // pro Haus um 2 erhöt
        if (m_iMilk > 0 && !m_bADDInhaMax_1) {
            m_iMaxApartInhabs_1_1 += 2;
            m_bADDInhaMax_1 = true;
        }// if

        // Berechung der Spieler Ressourcen nach jeweils 10 Sekunden
        if ((l_currenttime = (System.currentTimeMillis() - l_oldtime)) >= 20000) {
            l_oldtime = System.currentTimeMillis();
            // Berechung des aktuellen Geld Standes in Abähingkeit der Anzahl von Einwohnern
            m_iMoney += ((3 * m_iInhabitans_1) + (5 * m_iInhabitans_2));


            if (m_iWood > m_iSize) {
                m_iWood = m_iSize;
            }
            if (m_iBrick > m_iSize) {
                m_iBrick = m_iSize;
            }
            if (m_iTools > m_iSize) {
                m_iTools = m_iSize;
            }
            // Fische berechnen und ggf. Einwohnerzahl berechnen
            if ((l_currenttime2 = (System.currentTimeMillis() - l_oldtime2)) >= 20000) {
                l_oldtime2 = System.currentTimeMillis();
                if (m_iInhabitans_1 + m_iInhabitans_2 == 1) {
                    m_iFishes--;
                }
                if ((m_iFishes -= ((m_iInhabitans_1 + m_iInhabitans_2) / 2)) > m_iSize) {
                    m_iFishes = m_iSize;
                }
            }// if Fish
            // Milch berechnen und ggf. Einwohnerzahl berechnen
            if ((l_currenttime3 = (System.currentTimeMillis() - l_oldtime3)) >= 20000) {
                l_oldtime3 = System.currentTimeMillis();
                if (m_iInhabitans_1 + m_iInhabitans_2 == 1) {
                    m_iMilk--;
                }
                if ((m_iMilk -= (m_iInhabitans_1 + m_iInhabitans_2) / 2) > m_iSize) {
                    m_iMilk = m_iSize;
                }
            }// if Milk

            if (m_iSlime > m_iSize) {
                m_iSlime = m_iSize;
            }

            if (m_iWood < 0) {
                m_iWood = 0;
            }
            if (m_iBrick < 0) {
                m_iBrick = 0;
            }
            if (m_iTools < 0) {
                m_iTools = 0;
            }
            if (m_iFishes < 0) {
                m_iFishes = 0;
            }
            if (m_iMilk < 0) {
                m_iMilk = 0;
            }
            if (m_iSlime < 0) {
                m_iSlime = 0;
            }


            // Erhöung der Einwohnerzahl Stufe 1, bei ausreichendem Fisch im Lager
            if (m_iFishes >= 2 && m_iInhabitans_1 <= (m_iMaxApartInhabs_1_1 * m_iApartments_1)
                    && m_iInhabitans_1 > 0 && !m_bADDInhaMax_1) {
                m_iInhabitans_1 += 2;
            }
            // Oder veringern der Einwohenerzahl Stufe 1, bei zu wenig Fischen im Lager
            if (m_iFishes <= 1) {
                m_iInhabitans_1 -= 1;
            }
            if (m_iMilk <= 1 && m_iInhabitans_1 >= ((m_iMaxApartInhabs_1_1 - 2) * m_iApartments_1)
                    && m_bADDInhaMax_1) {
                m_iInhabitans_1 -= 1;
            }

            // Erhöung der Einwohnerzahl Stufe 1, bei ausreichendem Fisch und Milch im Lager
            if (m_iFishes >= 2 && m_iMilk >= 7 && m_iInhabitans_1 < (m_iMaxApartInhabs_1_1 * m_iApartments_1)
                    && m_iInhabitans_1 > 0 && m_bADDInhaMax_1) {
                m_iInhabitans_1 += 2;
            }

            // Gibt es mehr Einwohner als es Wohnplatzt gibt(z.B. wenn ein Haus abgerissen wurde
            // müssen die überschüssigen Einwohner ausziehen
            // noch ändern !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!111
            if (m_iInhabitans_1 > (m_iApartments_1 * m_iMaxApartInhabs_1_1)) {
                m_iInhabitans_1 = (m_iApartments_1 * m_iMaxApartInhabs_1_1);
            }
            if (m_iInhabitans_1 < 0) {
                m_iInhabitans_1 = 0;
            }
            // Oder veringern der Einwohenerzahl Stufe 1, bei zu wenig Milch im Lager
        else if(m_iMilk < (m_iInhabitans_1 % 4) && m_iInhabitans_1 > 0 &&
            m_iInhabitans_1 > ((m_iMaxApartInhabs_1_1 - 2) * m_iApartments_1))
            m_iInhabitans_1 -= 1;
            // 
            // Also Fisch + Milch + Tempel*/

        }
        if (m_iInhabitans_1 >= 50 && !m_bBuildElements_1) {
            m_bBuildElements_1 = true;
        }
    }// countResources
    
    // count the resources again
    //
    public void countResourcesCountRessources(int ID, int Pro, int Money) {
        switch (ID) {
            case (7):
                m_iWood += Pro;
                break;
            case (9):
                m_iSlime += Pro;
                break;
            case (10):
                m_iBrick += Pro;
                break;
            case (11):
                m_iFishes += Pro;
                break;
            case (12):
                m_iMilk += Pro;
                //cout << "Pro: " << Pro << "Milk:" << m_iMilk;
                break;
            default:
                break;
        }// switch
        m_iMoney -= Money;

        if (m_iWood > m_iSize) {
            m_iWood = m_iSize;
        }
        if (m_iBrick > m_iSize) {
            m_iBrick = m_iSize;
        }
        if (m_iTools > m_iSize) {
            m_iTools = m_iSize;
        }
        if ((m_iFishes /*-=  (m_iInhabitans_1 + m_iInhabitans_2)*/) > m_iSize) {
            m_iFishes = m_iSize;
        }
        if (m_iMilk > m_iSize) {
            m_iMilk = m_iSize;
        }
        if (m_iSlime > m_iSize) {
            m_iSlime = m_iSize;
        }

        if (m_iWood < 0) {
            m_iWood = 0;
        }
        if (m_iBrick < 0) {
            m_iBrick = 0;
        }
        if (m_iTools < 0) {
            m_iTools = 0;
        }
        if (m_iFishes < 0) {
            m_iFishes = 0;
        }
        if (m_iMilk < 0) {
            m_iMilk = 0;
        }
        if (m_iSlime < 0) {
            m_iSlime = 0;
        }
    }
    
    // return all infos about the resources to save
    //
    public String getResources(){
        String res = "<Resources Player id=" + s_playerID + " >";
        res += m_iInhabitans_1 + "/n" + m_iInhabitans_2 + "/n" + m_iMoney + "/n" +
                m_iWood + "/n" + m_iSlime + "/n" + m_iBrick + "/n" + m_iStones +
                "/n" + m_iFishes + "/n" + m_iMilk + "/n" + m_iTools + "/n" +
                m_iMoneyFactor + "/n" + m_iWoodFactor + "/n" + m_iSlimeFactor + "/n" +
                m_iBrickFactor + "/n" + m_iStoneFactor + "/n" + m_iFishFactor + "/n" +
                m_iMilkFactor + "/n" + (m_iApartments_1 - 2) + "/n" + m_iSize;
        
        return res;
    }// getResources
    
    // set all infos about resources by load
    //
    public void setResources(String res){
        String[] allrs = res.split("\n");
        
        m_iInhabitans_1 = Integer.parseInt(allrs[0]);
        m_iInhabitans_2 = Integer.parseInt(allrs[2]);
        m_iMoney = Integer.parseInt(allrs[3]);
        m_iWood = Integer.parseInt(allrs[4]);
        m_iSlime = Integer.parseInt(allrs[5]);
        m_iBrick = Integer.parseInt(allrs[6]);
        m_iStones = Integer.parseInt(allrs[7]);
        m_iFishes = Integer.parseInt(allrs[8]);
        m_iMilk = Integer.parseInt(allrs[9]);
        m_iTools = Integer.parseInt(allrs[10]);
        m_iMoneyFactor = Integer.parseInt(allrs[11]);
        m_iWoodFactor = Integer.parseInt(allrs[12]);
        m_iSlimeFactor = Integer.parseInt(allrs[13]);
        m_iBrickFactor = Integer.parseInt(allrs[14]);
        m_iStoneFactor = Integer.parseInt(allrs[15]);
        m_iFishFactor = Integer.parseInt(allrs[16]);
        m_iMilkFactor = Integer.parseInt(allrs[17]);
        m_iApartments_1 = Integer.parseInt(allrs[18]);  
    }// setResources
    
    // getter
    //
    public int getMoney(){return m_iMoney;}
    public int getWood(){return m_iWood;}
    public int getBrick(){return m_iBrick;}
    public int getInhabs(){return m_iInhabitans_1 + m_iInhabitans_2;}
    // attributes of the class
    //
    int m_iMoney;
    int m_iWood;
    int m_iBrick;
    int m_iStones;
    int m_iTools;
    int m_iFishes;
    int m_iMilk;
    int m_iSlime;
    int m_iInhabitans_1;
    int m_iInhabitans_2;
    int m_iApartments_1;
    int m_iApartments_2;
    
    int m_iMaxApartInhabs_1_1;
    int m_iMaxApartInhabs_1_2;
    int m_iMaxApartInhabs_2_1;
    int m_iMaxApartInhabs_2_2;
    int m_iSize;
    
    double m_iMoneyCosts;
   
    long l_oldtime;
    long l_oldtime2;
    long l_oldtime3;
    long l_currenttime;
    long l_currenttime2;
    long l_currenttime3;
    int m_iWoodFactor;
    int m_iBrickFactor;
    int m_iMoneyFactor;
    int m_iToolsFactor;
    int m_iFishFactor; 
    int m_iMilkFactor;
    int m_iSlimeFactor;
    int m_iStoneFactor; 
    
    boolean test;
    boolean m_bBuildElements_1;
    boolean m_bADDInhaMax_1;
    
    String s_playerID;
}// class Resources
