// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ee.itcollege.i377.team6.entities;

import ee.itcollege.i377.team6.entities.VahtkonndPiiriloigul;
import java.lang.Long;

privileged aspect VahtkonndPiiriloigul_Roo_Entity {
    
    public static long VahtkonndPiiriloigul.countVahtkonndPiiriloiguls() {
        return entityManager().createQuery("SELECT COUNT(o) FROM VahtkonndPiiriloigul o", Long.class).getSingleResult();
    }
    
    public static VahtkonndPiiriloigul VahtkonndPiiriloigul.findVahtkonndPiiriloigul(Long id) {
        if (id == null) return null;
        return entityManager().find(VahtkonndPiiriloigul.class, id);
    }
    
}
