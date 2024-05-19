package spring.learn.spring.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Utils {
    public static String LOCAL_STORAGE = "Storage",
            LOCAL_IMAGE_STORAGE = "Storage/Images";

    public static void uploadFile() {

    }

    public static void createFolderIfNotExists(String dirName) throws SecurityException {
        File theDir = new File(dirName);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
    }

    public static void saveToFile(InputStream inStream, String target) throws IOException {
        OutputStream out = null;
        int read = 0;
        byte[] bytes = new byte[1024];
        out = new FileOutputStream(new File(target));
        while ((read = inStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.flush();
        out.close();
    }

    public static String generateRandomCode() {
        Random rand = new Random();
        String code = "";
        for (int i = 0; i < 4; i++) {
            char c = (char) (rand.nextInt(60) + 69);
            code += c;
        }
        return Base64.getEncoder().withoutPadding().encodeToString(code.getBytes());
    }

    public static Object getObjectFromJson(String jsonObject, Class<?> objectClass) {
        try {
            Object obj;
            final GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            final Gson gson = builder.create();
            obj = gson.fromJson(jsonObject, objectClass);
            return obj;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getJsonFromObject(Object obj, Class<?> ojectClass) {
        try {
            String json = null;
            final GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            final Gson gson = builder.create();
            json = gson.toJson(obj, ojectClass);
            return json;
        } catch (Exception e) {
            String error = "Errors while converting";
            String json = null;
            final GsonBuilder builder = new GsonBuilder();
            final Gson gson = builder.create();
            json = gson.toJson(error, String.class);
            e.printStackTrace();
            return json;
        }
    }

    public static String generateRandomUserPassword() {
        Random rand = new Random();
        String password = "";
        for (int i = 0; i < 6; i++) {
            char c = (char) (rand.nextInt(60) + 69);
            password += c;
        }
        return Base64.getEncoder().withoutPadding().encodeToString(password.getBytes());
    }

    public static List<Object> castToObject(List<?> DTOList) {
        List<Object> objList = new ArrayList<>();
        for (Object object : DTOList)
            objList.add(object);
        return objList;
    }

    public static Date getDatePrime(String month, int currentYear) {
        Date datePrime = null;
        if (month.equals("febuary")) {
            String pattern = currentYear + "-02-28";
            datePrime = parseDate(pattern);
        } else if (month.equals("may")) {
            String pattern = currentYear + "-05-31";
            datePrime = parseDate(pattern);
        } else if (month.equals("august")) {
            String pattern = currentYear + "-08-31";
            datePrime = parseDate(pattern);
        } else if (month.equals("december")) {
            String pattern = currentYear + "-12-31";
            datePrime = parseDate(pattern);
        }
        return datePrime;
    }

    public static List<Date> getDate(String month, int currentYear) {
        List<Date> dates = new ArrayList<Date>();
        if (month.equals("febuary")) {
            dates.add(parseDate(currentYear - 1 + "-12-31"));
            dates.add(parseDate(currentYear + "-02-28"));
        } else if (month.equals("may")) {
            dates.add(parseDate(currentYear + "-02-28"));
            dates.add(parseDate(currentYear + "-05-31"));
        } else if (month.equals("august")) {
            dates.add(parseDate(currentYear + "-05-31"));
            dates.add(parseDate(currentYear + "-08-31"));
        } else if (month.equals("december")) {
            dates.add(parseDate(currentYear + "-08-31"));
            dates.add(parseDate(currentYear + "-12-31"));
        }
        return dates;
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date calculateDateFinPrevu(Date dateDebut, BigDecimal estime) {
        LocalDate inputDate = dateDebut.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate outputDate = inputDate.plusDays(Integer.valueOf(estime.intValue()));
        Date dateFinPrevu = Date.from(outputDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return dateFinPrevu;
    }

    public static Date vikunjaDateToJavaDate(String vikunjaDateStr) throws Exception {
        String pattern1 = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        String pattern2 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        Date javaDate = null;
        SimpleDateFormat format = null;
        if (vikunjaDateStr.length() > 20) {
            format = new SimpleDateFormat(pattern2);
            javaDate = format.parse(vikunjaDateStr);
        } else {
            format = new SimpleDateFormat(pattern1);
            javaDate = format.parse(vikunjaDateStr);
        }
        return javaDate;
    }

    public static String javaDateToVikunjaDate(Date javaDate) {
        SimpleDateFormat vikunjaDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String vikunjaDateStr = vikunjaDateFormat.format(javaDate);
        return vikunjaDateStr;
    }

    public static String convertVikunjaDateToIHM(Date vikunjaDate) {
        String vikunjaDateString = javaDateToVikunjaDate(vikunjaDate);

        if ((vikunjaDateString == null) || (vikunjaDateString != null && vikunjaDateString.length() == 0)) {
            return null;
        }
        return vikunjaDateString.split("T")[0].split("-")[2] + "/"
                + vikunjaDateString.split("T")[0].split("-")[1] + "/"
                + vikunjaDateString.split("T")[0].split("-")[0];

    }

}
