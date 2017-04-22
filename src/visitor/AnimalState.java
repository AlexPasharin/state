/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitor;

/**
 *
 * @author Alex
 */
abstract class AnimalState {
    
    
    public void changeState(Animal animal, AnimalState state){
        animal.setState(state);
    }
    
    abstract void animalEats(Animal animal);
    
    abstract void animalSleeps(Animal animal);
    
    abstract void animalHunts(Animal hunter, Animal victum);
    
}
