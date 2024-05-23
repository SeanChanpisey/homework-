package exception;


     public class CourseNotFoundException extends Exception {
        public CourseNotFoundException(String message) {
            super(message);
        }

        // Custom exception for an invalid option

    }
    class InvalidOptionException extends Exception {
    public InvalidOptionException(String message) {
        super(message);
    }
}
   class CourseNameNotFoundException extends Exception {
         public CourseNameNotFoundException(String message) {
             super(message);
         }
   }

