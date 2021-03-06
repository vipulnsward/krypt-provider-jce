/***** BEGIN LICENSE BLOCK *****
* Version: CPL 1.0/GPL 2.0/LGPL 2.1
*
* The contents of this file are subject to the Common Public
* License Version 1.0 (the "License"); you may not use this file
* except in compliance with the License. You may obtain a copy of
* the License at http://www.eclipse.org/legal/cpl-v10.html
*
* Software distributed under the License is distributed on an "AS
* IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
* implied. See the License for the specific language governing
* rights and limitations under the License.
*
* Copyright (C) 2011 
* Hiroshi Nakamura <nahi@ruby-lang.org>
* Martin Bosslet <Martin.Bosslet@googlemail.com>
*
* Alternatively, the contents of this file may be used under the terms of
* either of the GNU General Public License Version 2 or later (the "GPL"),
* or the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
* in which case the provisions of the GPL or the LGPL are applicable instead
* of those above. If you wish to allow use of your version of this file only
* under the terms of either the GPL or the LGPL, and not to allow others to
* use your version of this file under the terms of the CPL, indicate your
* decision by deleting the provisions above and replace them with the notice
* and other provisions required by the GPL or the LGPL. If you do not delete
* the provisions above, a recipient may use your version of this file under
* the terms of any one of the CPL, the GPL or the LGPL.
 */
package org.jruby.ext.krypt.provider.jce;

import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import org.jruby.ext.krypt.provider.Cipher;
import org.jruby.ext.krypt.provider.Digest;
import org.jruby.ext.krypt.provider.KryptProvider;
import org.jruby.ext.krypt.provider.jce.digest.JceCipher;
import org.jruby.ext.krypt.provider.jce.digest.JceDigest;

/**
 * 
 * @author <a href="mailto:Martin.Bosslet@googlemail.com">Martin Bosslet</a>
 */
public class KryptJceProvider implements KryptProvider {

    private KryptJceProvider() {}
    
    private static final KryptJceProvider INSTANCE = new KryptJceProvider();
    
    public static KryptProvider getInstance() {
        return INSTANCE;
    }
    
    @Override
    public Digest newDigestByName(String name) throws NoSuchAlgorithmException {
        return new JceDigest(Algorithms.getJavaAlgorithm(name));
    }

    @Override
    public Digest newDigestByOid(String oid) throws NoSuchAlgorithmException {
        return new JceDigest(Algorithms.getJavaAlgorithmForOid(oid));
    }
    
    @Override
    public Cipher newCipherByName(String name) throws NoSuchAlgorithmException,NoSuchPaddingException {
        return new JceCipher(Algorithms.getJavaAlgorithm(name));
    }

    //Do we do this?
    @Override
    public Cipher newCipherByOid(String oid) throws NoSuchAlgorithmException, NoSuchPaddingException {
            return new JceCipher(Algorithms.getJavaAlgorithmForOid(oid));
    }
    
    
}
