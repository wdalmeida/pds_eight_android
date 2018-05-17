/*******************************************************************************
* Copyright (c), NXP Semiconductors Gratkorn / Austria
*
* (C)NXP Semiconductors
* All rights are reserved. Reproduction in whole or in part is
* prohibited without the written consent of the copyright owner.
* NXP reserves the right to make changes without notice at any time.
* NXP makes no warranty, expressed, implied or statutory, including but
* not limited to any implied warranty of merchantability or fitness for any
* particular purpose, or that the use will not infringe any third party patent,
* copyright or trademark. NXP must not be liable for any loss or damage
* arising from its use.
********************************************************************************
*
* Filename: NFCManager.java
*
*
*******************************************************************************/

package fr.esipe.ing3.pds.eight.bem.cardpayement;

import android.content.Context;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.widget.Toast;

import java.nio.charset.Charset;

public class NFCManager {

	private Context context;

	public void setContext(Context context) {
		this.context = context;
	}

	private static NFCManager instance;

	public NFCManager() {
		init();
	}

	private void init() {

	}

	public static NFCManager getInstance() {
		if (instance == null)
			instance = new NFCManager();

		return instance;
	}


	public void writeToDevice(String msgToWrite, Tag tag) {

		if (tag == null) {
			Toast.makeText(context, "No Tag. Waiting...", Toast.LENGTH_LONG)
					.show();
			return;
		}

		Ndef ndefref = Ndef.get(tag);

		try {
            assert ndefref != null;
            ndefref.connect();

			try {

				ndefref.writeNdefMessage(createMessage(msgToWrite));

				Toast.makeText(context, "Writing message successful",
						Toast.LENGTH_SHORT).show();
			} catch (Exception e) {
				Toast.makeText(context,
						"Writing message failed : " + e.getMessage(),
						Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			Toast.makeText(context, "No Tag. Waiting...", Toast.LENGTH_LONG)
					.show();
			return;
		}

		try {
			ndefref.close();
		} catch (Exception e) {
            //TODO
		}
    }

	private NdefMessage createMessage(String text) {

		NdefRecord[] record = new NdefRecord[1];

		String lang = "en";

		byte[] langBytes = lang.getBytes(Charset.forName("US-ASCII"));

        byte[] textBytes = text.getBytes(Charset.forName("UTF-8"));

		char status = (char) (langBytes.length);

		byte[] data = new byte[1 + langBytes.length + textBytes.length];

		data[0] = (byte) status;

		System.arraycopy(langBytes, 0, data, 1, langBytes.length);
		System.arraycopy(textBytes, 0, data, 1 + langBytes.length,
				textBytes.length);

		record[0] = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
				NdefRecord.RTD_TEXT, new byte[0], data);

		return new NdefMessage(record);
	}

	public boolean checkDeviceHasNFC() {

        return getNFCAdapter() != null && getNFCAdapter().isEnabled();

    }

	public NfcAdapter getNFCAdapter() {
		NfcManager manager = (NfcManager) context
				.getSystemService(Context.NFC_SERVICE);

        return manager.getDefaultAdapter();
	}

}