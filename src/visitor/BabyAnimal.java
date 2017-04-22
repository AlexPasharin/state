/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitor;

import java.util.logging.Level;
import java.util.logging.Logger;

/** Animal is a baby until it reaches certain weight
 * Baby never hunts, sleeps as long as it needs and always ready to eat
 * Always sleeps after food
 *
 * @author Alex
 */
public class BabyAnimal extends AnimalState{
    
    // threshold for a maximal weight, after which animal grows up
    private static final int MAXIMAL_WEIGHT = 10;
    
    private static BabyAnimal INSTANCE;
    
    private BabyAnimal(){}
    
    // thread-safe double checking lazy singleton initialization
    public static BabyAnimal getInstance(){
        if(INSTANCE == null){
            synchronized(BabyAnimal.class){
                if(INSTANCE == null){
                    INSTANCE = new BabyAnimal();
                }               
            }           
        }       
        return INSTANCE;
    }
    
   
    // baby animal is always ready to eat
    @Override
    public void animalEats(Animal animal) {
        animal.setSleeping(false);
        animal.setEating(true);
        try {
            Thread.sleep(10000); // baby animal eats for 10 seconds
        } catch (InterruptedException ex) {
            Logger.getLogger(BabyAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }
        animal.addWeight(2);
        
        if(animal.getWeight() > MAXIMAL_WEIGHT){// animal grows up 
            animal.growUp();
        }
        else animalSleeps(animal); // baby animal sleeps after eating
    }

    // baby animal sleeps always when not eating
    @Override
    public void animalSleeps(Animal animal) {
        animal.setSleeping(true);
    }

    @Override
    public void animalHunts(Animal hunter, Animal victum) {
        // empty implementation, coz baby animal doesnt hunt
    }
    
    
}
