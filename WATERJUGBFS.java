import java.util.*;

public class WATERJUGBFS {
    public static void main(String[] args){
        int target = 5;
        int capacityOfA = 4;
        int capacityOfB = 3;

        WATERJUGBFS solve = new WATERJUGBFS();
        solve.solvePuzzle(target,capacityOfA,capacityOfB);
    }
    public void solvePuzzle(int target, int capacityOfA, int capacityOfB){
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        State root = new State(0, 0);
        queue.add(root);
        while(!queue.isEmpty()){
            State current = queue.poll();
            visited.add(new State(current.jugA, current.jugB));

            // check if we get the desired result
            if(current.jugA == target || current.jugB == target){
                System.out.println("Solution found Jug-A "+current.jugA+ " Jug-B " +current.jugB );
                return;
            }

            // now check for other neighbor types cases
            for(State newState : getNextState(current, capacityOfA, capacityOfB)){
                if(isValid(newState, capacityOfA, capacityOfB) && !visited.contains(newState)){
                    // visited.add(new State(newState.jugA, newState.jugB));
                    queue.add(newState);
                }
            }
        }
        System.out.println("No solution found");
    }

    public boolean isValid(State state, int A, int B){
        if(state.jugA >= 0 && state.jugA <= A && state.jugB >= 0 && state.jugB <= B){
            return true;
        }
        return false;
    }

    public List<State> getNextState(State currentState , int capacityOfA, int capacityOfB){
        List<State>  nextState = new ArrayList<>();
        // fill jug A
        nextState.add(new State(capacityOfA, currentState.jugB));

        // fill jubB
        nextState.add(new State(currentState.jugA, capacityOfB));

        // empty A
        nextState.add(new State(0, currentState.jugB));

        // empty B
        nextState.add(new State(currentState.jugA, 0));

        // pour water from A to B
        int pourAB = Math.min(currentState.jugA, capacityOfB - currentState.jugB);
        nextState.add(new State(currentState.jugA - pourAB, currentState.jugB + pourAB));

        // pour water from B to A
        int pourBA = Math.min(capacityOfA - currentState.jugA, currentState.jugB);
        nextState.add(new State(capacityOfA + pourAB, capacityOfB - pourBA));

        return nextState;
    }

    class State{
        private int jugA;
        private int jugB;

        public State(int jugA, int jugB){
            this.jugA = jugA;
            this.jugB = jugB;
        }

        @Override
    public int hashCode() {
        return jugA * 31 + jugB;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        State state = (State) obj;
        return jugA == state.jugA && jugB == state.jugB;
    }
    }
}
