package team.mediasoft.mguseva.eduproject;

public abstract class InputChecker {
    /**
     * Получить число из строки
     *
     * @param valueStr
     * @return
     */
    protected Integer getIntegerFromString(String valueStr) {
        int value = 0;
        try {
            value = Integer.parseInt(valueStr);
        } catch (Exception e) {}

        return (Integer) value;
    }
}
