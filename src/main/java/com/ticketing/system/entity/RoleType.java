package com.ticketing.system.entity;

/**
 * RoleType Enum - Defines all possible roles in the system
 *
 * Using enum is safer than strings because:
 * - Type-safe: Can't accidentally create role "CUSTOMR" (typo)
 * - Compile-time checking: If you use wrong role, won't compile
 * - Better for database integrity
 */
public enum RoleType {
    ADMIN,
    USER,
    AGENT
}
