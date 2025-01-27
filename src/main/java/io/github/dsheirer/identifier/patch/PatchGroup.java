/*
 * *****************************************************************************
 * Copyright (C) 2014-2023 Dennis Sheirer
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

package io.github.dsheirer.identifier.patch;

import io.github.dsheirer.identifier.radio.RadioIdentifier;
import io.github.dsheirer.identifier.talkgroup.TalkgroupIdentifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Temporary grouping or regrouping of talkgroups or individual radios.
 */
public class PatchGroup
{
    private TalkgroupIdentifier mPatchGroupIdentifier;
    private List<TalkgroupIdentifier> mPatchedTalkgroupIdentifiers = new ArrayList<>();
    private List<RadioIdentifier> mPatchedRadioIdentifiers = new ArrayList<>();
    private int mVersion;

    /**
     * Constructs an instance
     * @param patchGroupIdentifier for the patch group
     * @param version for this patch group identifier.  Note: if the patch group identifier is frequently reused, a
     * new version number will indicate a new instance of the patch group.
     */
    public PatchGroup(TalkgroupIdentifier patchGroupIdentifier, int version)
    {
        mPatchGroupIdentifier = patchGroupIdentifier;
        mVersion = version;
    }

    /**
     * Constructs an instance
     * @param patchGroupIdentifier that is the overall identifier for this temporary grouping.
     */
    public PatchGroup(TalkgroupIdentifier patchGroupIdentifier)
    {
        this(patchGroupIdentifier, 0);
    }

    /**
     * Version number for this patch group used to differentiate frequently reused patch group identifiers.
     * @return version number (aka super group sequence number).
     */
    public int getVersion()
    {
        return mVersion;
    }

    /**
     * Patch group identifier.
     * @return identifier
     */
    public TalkgroupIdentifier getPatchGroup()
    {
        return mPatchGroupIdentifier;
    }

    /**
     * Indicates if this patch group contains any patched talkgroups.
     * @return true if there are patched talkgroups.
     */
    public boolean hasPatchedTalkgroups()
    {
        return !getPatchedTalkgroupIdentifiers().isEmpty();
    }

    /**
     * Indicates if this patch group contains any patched radio identifiers.
     * @return true if there are patched radios.
     */
    public boolean hasPatchedRadios()
    {
        return !getPatchedRadioIdentifiers().isEmpty();
    }

    /**
     * Adds the talkgroup identifier to this patched group.
     * @param patchedGroupIdentifier to add
     */
    public void addPatchedTalkgroup(TalkgroupIdentifier patchedGroupIdentifier)
    {
        if(!mPatchedTalkgroupIdentifiers.contains(patchedGroupIdentifier))
        {
            mPatchedTalkgroupIdentifiers.add(patchedGroupIdentifier);
        }
    }

    /**
     * Adds a list of talkgroup identifiers to this patched group.
     * @param patchedGroupIdentifiers to add
     */
    public void addPatchedTalkgroups(List<TalkgroupIdentifier> patchedGroupIdentifiers)
    {
        for(TalkgroupIdentifier identifier : patchedGroupIdentifiers)
        {
            addPatchedTalkgroup(identifier);
        }
    }

    /**
     * List of patched group identifiers that are part of this patch group.
     * @return patched talkgroups.
     */
    public List<TalkgroupIdentifier> getPatchedTalkgroupIdentifiers()
    {
        return mPatchedTalkgroupIdentifiers;
    }

    /**
     * Adds the radio identifier to this patched group.
     * @param patchedRadioIdentifier to add
     */
    public void addPatchedRadio(RadioIdentifier patchedRadioIdentifier)
    {
        if(!mPatchedRadioIdentifiers.contains(patchedRadioIdentifier))
        {
            mPatchedRadioIdentifiers.add(patchedRadioIdentifier);
        }
    }

    /**
     * Adds a list of radio identifiers to this patched group.
     * @param patchedRadioIdentifiers to add
     */
    public void addPatchedRadios(List<RadioIdentifier> patchedRadioIdentifiers)
    {
        for(RadioIdentifier identifier : patchedRadioIdentifiers)
        {
            addPatchedRadio(identifier);
        }
    }

    /**
     * List of patched radio identifiers that are part of this patch group.
     * @return patched radios.
     */
    public List<RadioIdentifier> getPatchedRadioIdentifiers()
    {
        return mPatchedRadioIdentifiers;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("P:").append(getPatchGroup());

        if(hasPatchedTalkgroups())
        {
            sb.append(" TG").append(getPatchedTalkgroupIdentifiers());
        }

        if(hasPatchedRadios())
        {
            sb.append(" RA").append(getPatchedRadioIdentifiers());
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(o == null || getClass() != o.getClass())
        {
            return false;
        }
        PatchGroup that = (PatchGroup)o;
        return Objects.equals(mPatchGroupIdentifier, that.mPatchGroupIdentifier);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(mPatchGroupIdentifier);
    }
}
