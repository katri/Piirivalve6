// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ee.itcollege.i377.team6.entities;

import java.lang.String;

privileged aspect Vahtkond_Roo_ToString {
    
    public String Vahtkond.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Alates: ").append(getAlates()).append(", ");
        sb.append("Avaja: ").append(getAvaja()).append(", ");
        sb.append("Avatud: ").append(getAvatud() == null ? "null" : getAvatud().getTime()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Kommentaar: ").append(getKommentaar()).append(", ");
        sb.append("Kood: ").append(getKood()).append(", ");
        sb.append("Kuni: ").append(getKuni()).append(", ");
        sb.append("Muudetud: ").append(getMuudetud() == null ? "null" : getMuudetud().getTime()).append(", ");
        sb.append("Muutja: ").append(getMuutja()).append(", ");
        sb.append("Nimetus: ").append(getNimetus()).append(", ");
        sb.append("Piiripunkt: ").append(getPiiripunkt()).append(", ");
        sb.append("Suletud: ").append(getSuletud() == null ? "null" : getSuletud().getTime()).append(", ");
        sb.append("Sulgeja: ").append(getSulgeja()).append(", ");
        sb.append("Vaeosa: ").append(getVaeosa()).append(", ");
        sb.append("VahtkondId: ").append(getVahtkondId()).append(", ");
        sb.append("VahtkonndPiiriloiguls: ").append(getVahtkonndPiiriloiguls() == null ? "null" : getVahtkonndPiiriloiguls().size()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }
    
}
