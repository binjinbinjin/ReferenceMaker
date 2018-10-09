package job.apply.reference.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "application.excel-file", ignoreUnknownFields = false)
public class ExcelFileConfig {

    /**Number of headers in Excel file*/
    private int numberOfHeaders;
    /**List of headers*/
    private List<String> headers = new ArrayList<>(Collections.singleton("String"));
    /**Index of each header in headers*/
    private HeadersIndex headersIndex = new HeadersIndex();
    /**Date info saved in Excel File*/
    private Date date = new Date();

    public ExcelFileConfig() { }

    public int getNumberOfHeaders() {
        return numberOfHeaders;
    }

    public void setNumberOfHeaders(int numberOfHeaders) {
        this.numberOfHeaders = numberOfHeaders;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public HeadersIndex getHeadersIndex() {
        return headersIndex;
    }

    public void setHeadersIndex(HeadersIndex headersIndex) {
        this.headersIndex = headersIndex;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static class HeadersIndex {
        /**Cell index of where the company info is located*/
        private int companyName;
        /**Cell index of where the applied date info is located*/
        private int appliedDate;
        /**Cell index of where the job title info is located*/
        private int jobTitle;
        /**Cell index of where the location info is located*/
        private int location;
        /**Cell index of where the name of reference file info is located*/
        private int fileName;
        /**Cell index of where the resume used info is located*/
        private int resumeName;
        /**Cell index of where the cover letter used info is located*/
        private int coverLetterName;

        public HeadersIndex(){}

        public int getCompanyName() {
            return companyName;
        }

        public void setCompanyName(int companyName) {
            this.companyName = companyName;
        }

        public int getAppliedDate() {
            return appliedDate;
        }

        public void setAppliedDate(int appliedDate) {
            this.appliedDate = appliedDate;
        }

        public int getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(int jobTitle) {
            this.jobTitle = jobTitle;
        }

        public int getLocation() {
            return location;
        }

        public void setLocation(int location) {
            this.location = location;
        }

        public int getFileName() {
            return fileName;
        }

        public void setFileName(int fileName) {
            this.fileName = fileName;
        }

        public int getResumeName() {
            return resumeName;
        }

        public void setResumeName(int resumeName) {
            this.resumeName = resumeName;
        }

        public int getCoverLetterName() {
            return coverLetterName;
        }

        public void setCoverLetterName(int coverLetterName) {
            this.coverLetterName = coverLetterName;
        }
    }

    public static class Date {
        /**String the separate the Month Day Year*/
        private String dateSeparator;
        /**Index of month in format MM DD YYYY */
        private int monthIndex;
        /**Index of day of month in format MM DD YYYY */
        private int dayIndex;
        /**Index of year in format MM DD YYYY */
        private int yearIndex;

        public Date() { }

        public String getDateSeparator() {
            return dateSeparator;
        }

        public void setDateSeparator(String dateSeparator) {
            this.dateSeparator = dateSeparator;
        }

        public int getMonthIndex() {
            return monthIndex;
        }

        public void setMonthIndex(int monthIndex) {
            this.monthIndex = monthIndex;
        }

        public int getDayIndex() {
            return dayIndex;
        }

        public void setDayIndex(int dayIndex) {
            this.dayIndex = dayIndex;
        }

        public int getYearIndex() {
            return yearIndex;
        }

        public void setYearIndex(int yearIndex) {
            this.yearIndex = yearIndex;
        }
    }

}
