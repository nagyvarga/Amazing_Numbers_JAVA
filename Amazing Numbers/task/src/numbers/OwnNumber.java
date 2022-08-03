package numbers;
import java.awt.event.PaintEvent;
import java.util.ArrayList;
import java.util.List;

public class OwnNumber {
    enum Prop {even, odd, buzz, duck, palindromic, gapful, spy, square, sunny, jumping, happy, sad}
    private final long number;

    public static boolean isMutuallyExclusiveProperties(List<String> properties, List<String> wrongProperties) {
        boolean isEvenExcl = properties.contains("-" + Prop.even.name()) && properties.contains(Prop.even.name());
        boolean isOddExcl = properties.contains("-" + Prop.odd.name()) && properties.contains(Prop.odd.name());
        boolean isBuzzExcl = properties.contains("-" + Prop.buzz.name()) && properties.contains(Prop.buzz.name());
        boolean isDuckExcl = properties.contains("-" + Prop.duck.name()) && properties.contains(Prop.duck.name());
        boolean isPalindromicExcl = properties.contains("-" + Prop.palindromic.name()) && properties.contains(Prop.palindromic.name());
        boolean isGapfulExcl = properties.contains("-" + Prop.gapful.name()) && properties.contains(Prop.gapful.name());
        boolean isSpyExcl = properties.contains("-" + Prop.spy.name()) && properties.contains(Prop.spy.name());
        boolean isSquareExcl = properties.contains("-" + Prop.square.name()) && properties.contains(Prop.square.name());
        boolean isSunnyExcl = properties.contains("-" + Prop.sunny.name()) && properties.contains(Prop.sunny.name());
        boolean isJumpingExcl = properties.contains("-" + Prop.jumping.name()) && properties.contains(Prop.jumping.name());
        boolean isHappyExcl = properties.contains("-" + Prop.happy.name()) && properties.contains(Prop.happy.name());
        boolean isSadExcl = properties.contains("-" + Prop.sad.name()) && properties.contains(Prop.sad.name());

        boolean isEvenOdd = properties.contains(Prop.even.name()) && properties.contains(Prop.odd.name());
        boolean isEvenOddExcl = properties.contains("-" + Prop.even.name()) && properties.contains("-" + Prop.odd.name());
        boolean isDuckSpy = properties.contains(Prop.duck.name()) && properties.contains(Prop.spy.name());
        boolean isDuckSpyExcl = properties.contains("-" + Prop.duck.name()) && properties.contains("-" + Prop.spy.name());
        boolean isSunnySquare = properties.contains(Prop.sunny.name()) && properties.contains(Prop.square.name());
        boolean isHappySad = properties.contains(Prop.happy.name()) && properties.contains(Prop.sad.name());
        boolean isHappySadExcl = properties.contains("-" + Prop.happy.name()) && properties.contains("-" + Prop.sad.name());

        List<String> wrong = new ArrayList<>();

        if (isEvenExcl) {
            wrong.add(Prop.even.name());
            wrong.add("-" + Prop.even.name());
        }
        if (isOddExcl) {
            wrong.add(Prop.odd.name());
            wrong.add("-" + Prop.odd.name());
        }
        if (isBuzzExcl) {
            wrong.add(Prop.buzz.name());
            wrong.add("-" + Prop.buzz.name());
        }
        if (isDuckExcl) {
            wrong.add(Prop.duck.name());
            wrong.add("-" + Prop.duck.name());
        }
        if (isPalindromicExcl) {
            wrong.add(Prop.palindromic.name());
            wrong.add("-" + Prop.palindromic.name());
        }
        if (isGapfulExcl) {
            wrong.add(Prop.gapful.name());
            wrong.add("-" + Prop.gapful.name());
        }
        if (isSpyExcl) {
            wrong.add(Prop.spy.name());
            wrong.add("-" + Prop.spy.name());
        }
        if (isSquareExcl) {
            wrong.add(Prop.square.name());
            wrong.add("-" + Prop.square.name());
        }
        if (isSunnyExcl) {
            wrong.add(Prop.sunny.name());
            wrong.add("-" + Prop.sunny.name());
        }
        if (isJumpingExcl) {
            wrong.add(Prop.jumping.name());
            wrong.add("-" + Prop.jumping.name());
        }
        if (isHappyExcl) {
            wrong.add(Prop.happy.name());
            wrong.add("-" + Prop.happy.name());
        }
        if (isSadExcl) {
            wrong.add(Prop.sad.name());
            wrong.add("-" + Prop.sad.name());
        }

        if (isEvenOdd) {
            wrong.add(Prop.even.name());
            wrong.add(Prop.odd.name());
        }
        if (isEvenOddExcl) {
            wrong.add("-" + Prop.even.name());
            wrong.add("-" + Prop.odd.name());
        }
        if (isDuckSpy) {
            wrong.add(Prop.duck.name());
            wrong.add(Prop.spy.name());
        }
        if (isDuckSpyExcl) {
            wrong.add("-" + Prop.duck.name());
            wrong.add("-" + Prop.spy.name());
        }
        if (isSunnySquare) {
            wrong.add(Prop.sunny.name());
            wrong.add(Prop.square.name());
        }
        if (isHappySad) {
            wrong.add(Prop.happy.name());
            wrong.add(Prop.sad.name());
        }
        if (isHappySadExcl) {
            wrong.add("-" + Prop.happy.name());
            wrong.add("-" + Prop.sad.name());
        }

        for (String prop : properties) {
            if (wrong.contains(prop)) {
                wrongProperties.add(prop);
            }
        }

        return isEvenExcl || isOddExcl || isBuzzExcl || isDuckExcl || isPalindromicExcl || isGapfulExcl ||
                isSpyExcl || isSquareExcl || isSunnyExcl || isJumpingExcl || isHappyExcl || isSadExcl ||
                isEvenOddExcl || isDuckSpyExcl || isHappySadExcl ||
                isEvenOdd || isDuckSpy || isSunnySquare || isHappySad;
    }

    public static String getProperties() {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < Prop.values().length - 1; i++) {
            temp.append(Prop.values()[i]).append(", ");
        }
        temp.append(Prop.values()[Prop.values().length - 1]);
        return temp.toString();
    }

    public long getNumber() {
        return this.number;
    }
    public OwnNumber(long num) {
        this.number = num;
    }
    public boolean isOdd() {
        return number % 2 == 1;
    }

    public boolean isEven() {
        return !this.isOdd();
    }

    public boolean isNatural() {
        return number > 0;
    }
    public boolean isEndsWithSeven() {
        return number % 10 == 7;
    }
    public boolean isDivisibleBySeven() {
        return number % 7 == 0;
    }
    public boolean isBuzz() {
        return this.isEndsWithSeven() || this.isDivisibleBySeven();
    }

    public boolean isDuck() {
        boolean duck = false;
        long tempNum = number;
        do {
            long lastDigit = tempNum % 10;
            if (lastDigit == 0) {
                duck = true;
            }
            tempNum /= 10;
        } while (tempNum != 0 && !duck);
        return duck;
    }

    public boolean isPalindromic() {
        long reversedNumber = 0;
        long temp = number;
        do {
            long lastDigit = temp % 10;
            reversedNumber = reversedNumber * 10 + lastDigit;
            temp /= 10;
        } while (temp != 0);
        return reversedNumber == number;
    }

    public boolean isGapful() {
        long division = number % 10;
        long temp = number;
        int digit = 0;
        long lastDigit;
        do {
            digit++;
            lastDigit = temp;
            temp /= 10;
        } while (temp != 0);

        division += lastDigit * 10;

        return digit >= 3 && number % division == 0;
    }

    public boolean isSpy() {
        long sum = 0;
        long product = 1;
        long temp = number;
        do {
            sum += temp % 10;
            product *= temp % 10;
            temp /= 10;
        } while (temp != 0);

        return sum == product;
    }

    public boolean isSquare() {
        double temp = Math.sqrt(number);
        return temp == (double) ((int) temp);
    }

    public boolean isSunny() {
        double temp = Math.sqrt(number + 1);
        return temp == (double) ((int) temp);
    }

    public boolean isJumping() {
        long temp = number;
        if (number / 10 == 0) {
            return true;
        }
        boolean isJumping = true;
        long prev = number % 10;
        temp /= 10;
        while (temp != 0 && isJumping) {
            long last = temp % 10;
            isJumping = Math.abs(prev - last) == 1;
            temp /= 10;
            prev = last;
        }
        return isJumping;
    }

    public boolean isHappy() {
        long sum;
        long temp = number;
        long firstSum = 0;
        boolean isFirst = true;
        int counter = 0;
        do {
            counter++;
            sum = 0;
            do {
                long last = temp % 10;
                sum += (last * last);
                temp /= 10;
            } while (temp != 0);
            temp = sum;
            if (isFirst) {
                if (sum != 1) {
                    firstSum = sum;
                    sum = 0;
                }
                isFirst = false;
            }
        } while (sum != 1 && counter < 15 && firstSum != sum);

        return firstSum != sum && counter != 15;
    }

    public boolean isSad() {
        return !this.isHappy();
    }

}
