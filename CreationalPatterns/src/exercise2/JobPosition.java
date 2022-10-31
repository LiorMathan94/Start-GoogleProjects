package exercise2;

import java.time.LocalDate;

public class JobPosition {
    private final LocalDate publishDate;
    private final String title;
    private String description;
    private Location location;
    private int salaryCap;
    private boolean isRemote;
    private int yearsOfExperience;

    public static class Builder {
        // Required Parameters
        private final LocalDate publishDate;
        private final String title;
        private boolean isRemote;

        // Optional Parameters
        private String description;
        private Location location = Location.TEL_AVIV;
        private int salaryCap;
        private int yearsOfExperience;

        public Builder(LocalDate publishDate, String title, boolean isRemote) {
            this.publishDate = publishDate;
            this.title = title;
            this.isRemote = isRemote;
        }

        public Builder setRemote(boolean remote) {
            isRemote = remote;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setLocation(Location location) {
            this.location = location;
            return this;
        }

        public Builder setSalaryCap(int salaryCap) {
            this.salaryCap = salaryCap;
            return this;
        }

        public Builder setYearsOfExperience(int yearsOfExperience) {
            this.yearsOfExperience = yearsOfExperience;
            return this;
        }

        public JobPosition build() {
            return new JobPosition(this);
        }
    }

    private JobPosition(Builder builder) {
        this.publishDate = builder.publishDate;
        this.title = builder.title;
        this.isRemote = builder.isRemote;
        this.location = builder.location;
        this.salaryCap = builder.salaryCap;
        this.description = builder.description;
        this.yearsOfExperience = builder.yearsOfExperience;
    }
}
