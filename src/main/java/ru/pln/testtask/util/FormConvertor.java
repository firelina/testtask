package ru.pln.testtask.util;

import ru.pln.testtask.models.OrgLoyalForm;

public class FormConvertor {
    public static OrgLoyalForm stringToForm(String orgLoyalForm) {
        if (orgLoyalForm == null) {
            return OrgLoyalForm.SELF_LAID;
        }
        return switch (orgLoyalForm) {
            case "ИП" -> OrgLoyalForm.IP;
            case "АО" -> OrgLoyalForm.AO;
            case "ООО" -> OrgLoyalForm.OOO;
            case "самозанятый" -> OrgLoyalForm.SELF_LAID;
            default -> OrgLoyalForm.SELF_LAID;

        };
    }
    public static String formToString(OrgLoyalForm orgLoyalForm) {
        return switch (orgLoyalForm) {
            case IP -> "ИП";
            case AO -> "АО";
            case OOO -> "ООО";
            case SELF_LAID -> "самозанятый";
        };
    }
}
