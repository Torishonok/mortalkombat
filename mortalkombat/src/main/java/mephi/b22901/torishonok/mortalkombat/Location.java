/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi.b22901.torishonok.mortalkombat;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import mephi.b22901.torishonok.GUI.fighter.Enemy;
import mephi.b22901.torishonok.GUI.fighter.EnemyFactory;

/**
 *
 * @author vikus
 */
public class Location {
    private final ArrayList<Enemy> listEnemy;
    private final EnemyFactory enemyFactory = new EnemyFactory();
    private final int countEnemy;
    private final int locationNumber;
    private final Image locationImage;

    private static Image imageLocation1;
    private static Image imageLocation2;
    private static Image imageLocation3;
    private static Image imageLocation4;
    private static Image imageLocation5;
    private static Image imageLocation0;

    static {
        try {
            imageLocation1 = ImageIO.read(Location.class.getResourceAsStream("/локация1.jpg"));
            imageLocation2 = ImageIO.read(Location.class.getResourceAsStream("/локация2.jpg"));
            imageLocation3 = ImageIO.read(Location.class.getResourceAsStream("/локация3.jpg"));
            imageLocation4 = ImageIO.read(Location.class.getResourceAsStream("/локация4.jpg"));
            imageLocation5 = ImageIO.read(Location.class.getResourceAsStream("/локация5.jpg"));
            imageLocation0 = ImageIO.read(Location.class.getResourceAsStream("/мифи.jpg"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location(int numberLocation) {
        this.locationNumber = numberLocation;
        this.countEnemy = generateCountEnemy(numberLocation);
        this.listEnemy = generateLocation();
        this.locationImage = getLocationImage(numberLocation);
    }

    public ArrayList<Enemy> getListEnemy() {
        return listEnemy;
    }

    public int getLocationNumber() {
        return locationNumber;
    }


    private ArrayList<Enemy> generateLocation(){
        ArrayList<Enemy> temp = new ArrayList<>();
        for (int i = 0; i < this.countEnemy; i++) {
            if ((i + 1) < this.countEnemy) {
                temp.add(enemyFactory.createEnemy(new Random().nextInt(1,6)));
            } else {
                temp.add(enemyFactory.createEnemy(new Random().nextInt(6,7)));
            }
        }
        return temp;
    }

    int generateCountEnemy(int numberLocation) {
        switch (numberLocation) {
            case 1: return 2;
            case 2: return new Random().nextInt(2, 4);
            case 3: return new Random().nextInt(3, 5);
            case 4: return new Random().nextInt(4, 6);
            case 5: return new Random().nextInt(5, 7);
            default:
                System.out.println("Ошибка генерации локации!" + numberLocation);
                return 0;
        }
    }

    public static Image getLocationImage(int numberLevelLocation) {
        switch (numberLevelLocation) {
            case 1:
                return imageLocation1;
            case 2:
                return imageLocation2;
            case 3:
                return imageLocation3;
            case 4:
                return imageLocation4;
            case 5:
                return imageLocation5;
            default:
                return imageLocation0;
        }
    }
}
