package com.demo.project.tests;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OperatiiDobanda {
    private UserInterfaceDB userInterfaceDB;

    public OperatiiDobanda(UserInterfaceDB userInterfaceDB) {
        this.userInterfaceDB = userInterfaceDB;
    }

    public double calculDobanda(TipDobanda tipDobanda) {
        switch (tipDobanda) {
            case MICA -> {
                return 0.1;
            }
            case MEDIE -> {
                return 0.5;
            }

            case MARE -> {
                return 0.9;
            }
            default -> {
                return 0;
            }
        }
    }

    public TipDobanda calculDobandaCuRisc() {
        switch (this.userInterfaceDB.getUser().getRisc()) {
            case MIC -> {
                return TipDobanda.MARE;
            }

            case MEDIU -> {
                return TipDobanda.MEDIE;
            }

            default -> {
                return TipDobanda.MICA;
            }
        }

    }

}
