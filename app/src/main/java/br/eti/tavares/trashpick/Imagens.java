package br.eti.tavares.trashpick;

public class Imagens {

    public static int getDrawable(String nome) {
        switch (nome) {
            case "ic_jornal_round":
                return R.drawable.ic_jornal_round;
            case "ic_folha_papel_round":
                return R.drawable.ic_folha_papel_round;
            case "ic_esponja_round":
                return R.drawable.ic_esponja_round;
            case "ic_lataaco_round":
                return R.drawable.ic_lataaco_round;
            case "ic_lixoeletronico_round":
                return R.drawable.ic_lixoeletronico_round;
            case "ic_algodao_round":
                return R.drawable.ic_algodao_round;
            case "ic_copoisopor_round":
                return R.drawable.ic_copoisopor_round;
            case "ic_garrafavidro_round":
                return R.drawable.ic_garrafavidro_round;
            case "ic_embalagempet_round":
                return R.drawable.ic_embalagempet_round;
            case "ic_luvasborracha_round":
                return R.drawable.ic_luvasborracha_round;
            default:
                return R.drawable.logotp;
        }
    }

}
