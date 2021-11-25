/*
 * *****************************************************************************
 * Copyright (C) 2014-2021 Dennis Sheirer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 * ****************************************************************************
 */

package io.github.dsheirer.module.decode.ip.lrrp.token;

import io.github.dsheirer.bits.CorrectedBinaryMessage;

/**
 * LRRP 3D Position with latitude, longitude and altitude
 */
public class Position3D extends Position
{
    //Note: these may not be correct ... there may be 3 bytes available for altitude
    private static final int[] ALTITUDE_WHOLE = new int[]{72, 73, 74, 75, 76, 77, 78, 79};
    private static final int[] ALTITUDE_FRACTIONAL = new int[]{80, 81, 82, 83, 84, 85, 86, 87};

    /**
     * Constructs an instance of an approximate position token.
     *
     * @param message containing the heading
     * @param offset to the start of the token
     */
    public Position3D(CorrectedBinaryMessage message, int offset)
    {
        super(message, offset);
    }

    @Override
    public TokenType getTokenType()
    {
        return TokenType.POSITION_3D;
    }

    /**
     * Altitude
     *
     * @return altitude in meters
     */
    public float getAltitude()
    {
        return getFloat(ALTITUDE_WHOLE, ALTITUDE_FRACTIONAL);
    }

    @Override
    public String toString()
    {
        CorrectedBinaryMessage sub = getMessage().getSubMessage(getOffset(), getOffset() + 87);
        return "POSITION:" + getPosition() + " ALTITUDE:" + getAltitude() + " MTRS";
    }
}
