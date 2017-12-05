package org.magic7.utils;

import java.io.OutputStream;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheUtil {  
    private static CacheManager cacheManager = null;
    private static Cache imageCache = null; 
    private static Cache fileCache = null; 
    private static Cache dataCache = null; 
    static {
    	System.setProperty(net.sf.ehcache.CacheManager.ENABLE_SHUTDOWN_HOOK_PROPERTY,"true");
    	cacheManager = CacheManager.create(Thread.currentThread().getContextClassLoader().getResource("magic7_ehcache.xml"));
    	imageCache = cacheManager.getCache(CacheName.image.name()); 
    	fileCache = cacheManager.getCache(CacheName.file.name()); 
    	dataCache = cacheManager.getCache(CacheName.data.name()); 
    }
    public static enum CacheName{
    	image,
    	file,
    	data;
    };  
      
    public static void putImage(String key,Object value){  
        Element element = new Element(key, value);
	    imageCache.put(element);
        imageCache.flush();
    }  
    public static Object getImage(String key){  
        Element element =imageCache.get(key);  
        if(element!=null){  
            return element.getObjectValue();  
        }  
        return null;  
    } 
    public static void removeImage(String key){  
        imageCache.remove(key) ;
    }
    
    public static void putFile(String key,OutputStream value){  
        Element element = new Element(key, value);
        fileCache.put(element);
        fileCache.flush();
    }  
    public static OutputStream getFile(String key){  
        Element element =fileCache.get(key);  
        if(element!=null){  
            return (OutputStream)element.getObjectValue();  
        }  
        return null;  
    } 
    public static void removeFile(String key){  
    	fileCache.remove(key) ;
    }
    
    public static void putData(String key,OutputStream value){  
        Element element = new Element(key, value);
        dataCache.put(element);
        dataCache.flush();
    }  
    public static OutputStream getData(String key){  
        Element element =dataCache.get(key);  
        if(element!=null){  
            return (OutputStream)element.getObjectValue();  
        }  
        return null;  
    } 
    public static void removeData(String key){  
    	dataCache.remove(key) ;
    }
} 