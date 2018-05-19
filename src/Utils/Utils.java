package Utils;

import java.text.SimpleDateFormat;

import Enums.TenantType;

public class Utils {
	// region Public Methods
	public static SimpleDateFormat getSimpleDateFormat() {
		return new SimpleDateFormat("dd-MM-yyyy");
	}

	public static TenantType parseTenanTypetEnum(int type) {
		TenantType tenantType = TenantType.Tenant;

		switch (type) {
		case 0:
			tenantType = TenantType.Tenant;
			break;
		case 1:
			tenantType = TenantType.HouseCommittee;
			break;
		default:
			tenantType = TenantType.Tenant;
			break;
		}

		return tenantType;
	}

	public static int parseTenanTypetInteger(TenantType type) {
		int tenantType = 0;

		switch (type) {
		case Tenant:
			tenantType = 0;
			break;
		case HouseCommittee:
			tenantType = 1;
			break;
		default:
			tenantType = 0;
			break;
		}

		return tenantType;
	}
	// endregion
}
