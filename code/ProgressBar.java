public class ProgressBar {
    int steps;
    int maxSteps;
    int currentPercent;
    char[] bar = new char[102];
    public ProgressBar(int maxSteps){
        this.maxSteps = maxSteps;
        steps = 0;
        currentPercent = 1;
        init_progressbar();
    }

    private void init_progressbar(){
        bar[0] = '[';
        bar[101] = ']';
        for (int i = 1; i <= 100; i++) {
            bar[i] = ' ';
        }
        System.out.print(new String(bar) + "\r");
    }

    public void step(){
        steps++;
        double s  = (double) steps/maxSteps;
        if(Math.round(s * 100) >= currentPercent){
            currentPercent = (int) Math.round(s * 100);
            bar[currentPercent] = '=';
            System.out.print(new String(bar) + "\r");
        }

    }
}
