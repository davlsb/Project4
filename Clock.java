public class Clock {
   private int hours;
   private int minutes;
   private int seconds;

   public Clock(int hours, int minutes, int seconds) throws IllegalClockException {
      if (hours <= 24 && hours >= 0 && minutes >= 0 && minutes <= 60 && seconds >= 0 && seconds <= 60) {
         this.hours = hours;
         this.minutes = minutes;
         this.seconds = seconds;
      } else {
         throw new IllegalClockException("No such clock");
      }
   }

   public void setHours(int hours) {
      this.hours = hours;
   }

   public void setMinutes(int minutes) {
      this.minutes = minutes;
   }

   public void setSeconds(int seconds) {
      this.seconds = seconds;
   }

   public int getHours() {
      return this.hours;
   }

   public int getMinutes() {
      return this.minutes;
   }

   public int getSeconds() {
      return this.seconds;
   }

   public String toString() {
      return this.hours + ":" + this.minutes + ":" + this.seconds;
   }
}
