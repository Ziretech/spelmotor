package se.hoglund.josef.spelmotor;

import java.util.HashMap;

public class TangentbordBuilder {
    private final HashMap<Integer, Spelmotor.Tangenthanterare> nedtryck = new HashMap<>();
    private final HashMap<Integer, Spelmotor.Tangenthanterare> släpp = new HashMap<>();
    private final HashMap<Integer, Boolean> tangentstatus = new HashMap<>();

    public TangentbordBuilder registreraTangent(int tangent) {
        nedtryck.put(tangent, () -> tangentstatus.put(tangent, true));
        släpp.put(tangent, () -> tangentstatus.put(tangent, false));
        return this;
    }

    public Tangentbord build() {
        return new Tangentbord(nedtryck, släpp, tangentstatus);
    }

    public TangentbordBuilder registreraNedtryck(int tangent, Spelmotor.Tangenthanterare tangenthanterare) {
        nedtryck.put(tangent, tangenthanterare);
        return this;
    }
}
