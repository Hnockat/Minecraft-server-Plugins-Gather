package org.werewolf;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.util.Set;

public class GetYml {
    private final String FileName ;
    private FileConfiguration yaml;
    public GetYml (String FileName)
    {
        this.FileName = FileName;
    }
    public FileConfiguration 激活 ()
    {
        return yaml = YamlConfiguration.loadConfiguration(new File(Werewolf.getPlugin(Werewolf.class).getDataFolder(),FileName)/*返回主类的数据文件夹*/);
    }
    //此Float 为位移强转单个位移
    public Object Yml(String FileType, String FilePath)
    {
        激活();
        switch (FileType)
        {
            case "int" : {return yaml.getInt(FilePath);}
            case "double" : {return yaml.getDouble(FilePath);}
            case "String" : {return yaml.getString(FilePath,null);}
            case "boolean" : {return yaml.getBoolean(FilePath);}
            case "List" :{return yaml.getList(FilePath);}
            default : throw new NullPointerException();
        }
    }
    public boolean contains(String FilePath){ //检查是否有该key存在
        激活();
        return yaml.contains(FilePath);
    }
    public boolean contains_List_value(String FilePath,Object value) //检查该List key 是否有该元素
    {
        激活();
        return yaml.getList(FilePath).contains(value);
    }

    //返回Key 的路径名
    public Set<String> 获取该节点的所有子节点名字集合(String FilePath) {
        ConfigurationSection Config = 激活().getConfigurationSection(FilePath);
        if (Config != null) {
            return Config.getKeys(false);
        }
        return null;
    }

}