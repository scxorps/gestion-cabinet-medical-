package IHM.dashboard;

public class WaitingRoomClass {
    
   
        private String id;
        private String description;
        private String iddoc;
    
        public WaitingRoomClass(String id, String description, String iddoc) {
            this.id = id;
            this.description = description;
            this.iddoc = iddoc;
        }
    
        public String getId() {
            return id;
        }
    
        public String getDescription() {
            return description;
        }
        public String getIdDoc() {
            return iddoc;
        }
    
        public String toString() {
            return description;
        }
    
}
