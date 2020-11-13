import java.util.Arrays;
import java.util.stream.IntStream;

/*
all students must receive atlest one reward
any given student must receive strictly more rewards than the adjacent student with lower score
do two sweeps of the input list of scores , one from left to right and other from right to left
left-right -> ensures that the student is rewarded correctly wrt to student sitting left to him;
right-left -> ensures that the student is rewarded correctly wrt to student sitting right to him;
*/
class MinRewards {

    public static int minRewards(int[] scores) {
        int[] rewards = new int[scores.length];

        Arrays.fill(rewards, 1);

        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > scores[i - 1]) {
                rewards[i] = rewards[i - 1] + 1;
            }
        }

        for (int i = scores.length - 2; i >= 0; i--) {
            if (scores[i] > scores[i + 1]) {
                rewards[i] = Math.max(rewards[i], rewards[i + 1] + 1);
            }
        }
        return IntStream.of(rewards).sum();
    }

    public static void main(String[] args) {
        int[] scores = {8, 4, 2, 1, 3, 6, 7, 9, 5};
        int minRewards = minRewards(scores);
        System.out.println(minRewards);
    }
}