import java.util.HashSet;
import java.util.Set;

class State {
    int jugA, jugB;

    public State(int jugA, int jugB) {
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

public class WATERJUGDFS {

    public static boolean isValidState(State state, int capacityA, int capacityB) {
        return state.jugA >= 0 && state.jugB >= 0 && state.jugA <= capacityA && state.jugB <= capacityB;
    }

    public static void dfs(State currentState, int targetQuantity, int capacityA, int capacityB, Set<State> visited) {
        if (currentState.jugA == targetQuantity || currentState.jugB == targetQuantity) {
            System.out.println("Solution found: " + currentState.jugA + " " + currentState.jugB);
            return;
        }

        visited.add(currentState);

        for (State nextState : getNextStates(currentState, capacityA, capacityB)) {
            if (isValidState(nextState, capacityA, capacityB) && !visited.contains(nextState)) {
                dfs(nextState, targetQuantity, capacityA, capacityB, visited);
            }
        }
    }

    public static void solveWaterJugProblemDFS(int targetQuantity, int capacityA, int capacityB) {
        State initialState = new State(0, 0);
        Set<State> visited = new HashSet<>();
        dfs(initialState, targetQuantity, capacityA, capacityB, visited);

        System.out.println("No solution found.");
    }

    public static Set<State> getNextStates(State currentState, int capacityA, int capacityB) {
        Set<State> nextStates = new HashSet<>();

        // Fill jug A
        nextStates.add(new State(capacityA, currentState.jugB));

        // Fill jug B
        nextStates.add(new State(currentState.jugA, capacityB));

        // Empty jug A
        nextStates.add(new State(0, currentState.jugB));

        // Empty jug B
        nextStates.add(new State(currentState.jugA, 0));

        // Pour from A to B
        int pourAB = Math.min(currentState.jugA, capacityB - currentState.jugB);
        nextStates.add(new State(currentState.jugA - pourAB, currentState.jugB + pourAB));

        // Pour from B to A
        int pourBA = Math.min(currentState.jugB, capacityA - currentState.jugA);
        nextStates.add(new State(currentState.jugA + pourBA, currentState.jugB - pourBA));

        return nextStates;
    }

    public static void main(String[] args) {
        int targetQuantity = 4; // Set your target quantity here
        int capacityA = 4;      // Capacity of jug A
        int capacityB = 3;      // Capacity of jug B

        solveWaterJugProblemDFS(targetQuantity, capacityA, capacityB);
    }
}
