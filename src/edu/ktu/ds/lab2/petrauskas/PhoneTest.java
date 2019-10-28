package edu.ktu.ds.lab2.petrauskas;

import edu.ktu.ds.lab2.utils.Ks;
//import edu.ktu.ds.lab2.utils.LinkedList;

import java.util.LinkedList;

public class PhoneTest
{
    private LinkedList<Phone> allPhones;

    private PhoneTest()
    {
        allPhones = new LinkedList<>();
    }

    private void createPhones()
    {
        allPhones.add(new Phone(
                "Samsung",
                "Galaxy S8",
                3000,
                2017,
                400,
                5.8,
                true
        ));

        allPhones.add(new Phone(
                "Samsung",
                "Galaxy S10",
                3400,
                2019,
                570,
                6.1,
                true
        ));

        allPhones.add(new Phone(
                "Apple",
                "iPhone 11 Pro",
                3046,
                2019,
                1000,
                5.8,
                false
        ));

        String[] phoneInfos = {
            "Xiaomi Redmi Note 8 Pro 4500 2019 220.0 6.53 true",
            "OnePlus 7T 3800 2019 600.0 6.55 false"
        };

        for (String phoneData:phoneInfos)
        {
            var ph = new Phone(phoneData);
            if(ph.parsedSuccessfully())
            {
                allPhones.add(ph);
            }
        }
    }

    private void listPhones(LinkedList<Phone> phones)
    {
        for (Phone phone:phones)
        {
            Ks.oun(phone.toString());
        }
        Ks.oun(" ");
    }

    private LinkedList<Phone> getPhonesWithHeadphoneJack()
    {
        var phones = new LinkedList<Phone>();

        for (var phone: allPhones)
        {
            if(phone.getHasHeadphoneJack())
            {
                phones.add(phone);
            }
        }

        return phones;
    }

    private void run()
    {
        createPhones();
        listPhones(allPhones);
        //allPhones.sortBubble();
        //listPhones(allPhones);
        var phonesWithHeadphoneJack = getPhonesWithHeadphoneJack();
        listPhones(phonesWithHeadphoneJack);
    }

    public static void main(String... args)
    {
        new PhoneTest().run();
        Ks.oun("Phone test complete!");
    }
}
