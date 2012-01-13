/*
******************************************************************
File: org.mkcl.els.service.impl.MenuItemServiceImpl.java
Copyright (c) 2011, vishals, MKCL
All rights reserved.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.

******************************************************************
 */
package org.mkcl.els.service.impl;

import org.mkcl.els.domain.MenuItem;
import org.mkcl.els.service.IMenuItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The Class MenuItemServiceImpl.
 *
 * @author vishals
 * @version v1.0.0
 */
@Service
public class MenuItemServiceImpl extends GenericServiceImpl<MenuItem,Long> implements IMenuItemService{

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(MenuItemServiceImpl.class);

	/** The menu item repository. */
	//private MenuItemRepository menuItemRepository;

	/**
	 * Sets the message resource repository.
	 *
	 * @param menuItemRepository the new message resource repository
	 */
	/*@Autowired
	public void setRepository(final MenuItemRepository menuItemRepository) {
		this.dao = menuItemRepository;
		this.menuItemRepository = menuItemRepository;
	}*/

	/* (non-Javadoc)
	 * @see org.mkcl.els.service.IMenuItemService#getMenuXml()

	@Override*/
    /*public String getMenuXml() {
		List<MenuItem> items = dao.findAll();
		Element root = new Element("root");
		for (MenuItem item : items) {
			Element row = new Element("menu");
			row.setAttribute(new Attribute("id", item.getId() + ""));
			row.setAttribute(new Attribute("text", item.getText()));
			row.setAttribute(new Attribute("url", item.getUrl()));
            if (item.getParent() != null) {
                row.setAttribute(new Attribute("parent", item.getParent()
                        .getId() + ""));
            }
			root.addContent(row);
		}
		StringWriter writer = new StringWriter();
		XMLOutputter serializer = new XMLOutputter();
	    try {
			serializer.output(root, writer);
		} catch (IOException e) {
			logger.error(e.toString());
		}
		return writer.toString();
	}*/

	/*@Override
	public MenuItem findByTextKey(final String textKey)
	{
		return menuItemRepository.findMenuItemByTextKey(textKey);
	}*/

	/*@Override
	public String getMenuXml(final Locale locale) {
		List<MenuItem> items = menuItemRepository.findAllByLocale(locale.toString());
		Element root = new Element("root");
		for(MenuItem item:items){
			Element row = new Element("menu");
			row.setAttribute(new Attribute("id", item.getId()+""));
			row.setAttribute(new Attribute("text", item.getText()));
			row.setAttribute(new Attribute("url", item.getUrl()));
			if(item.getParent()!=null){
				row.setAttribute(new Attribute("parent", item.getParent().getId()+""));
			}
			root.addContent(row);
		}
		StringWriter writer = new StringWriter();
		XMLOutputter serializer = new XMLOutputter();
	    try {
			serializer.output(root, writer);
		} catch (IOException e) {
			logger.error(e.toString());
		}
		return writer.toString();
	}*/

	/**
	 * Code to get get menu structure in recursive format not needed in this implementation but can be used in future
	public String getMenu() {
		List<MenuItem> roots = menuItemRepository.findMenuItemsByParent(null);
		Element root = new Element("menu");
		for(MenuItem item: roots){
			Element ele = new Element("menu").setAttribute(new Attribute("text", item.getText()));
			//ele.addContent(new Element("text").addContent());
			root.addContent(ele);
			addMenuItem(item, ele);
		}
		StringWriter writer = new StringWriter();
		XMLOutputter serializer = new XMLOutputter();
	    try {
			serializer.output(root, writer);
		} catch (IOException e) {
			logger.error(e.toString());
		}
		return writer.toString();
	}

	public void addMenuItem(MenuItem parent, Element root){
		List<MenuItem> items = menuItemRepository.findMenuItemsByParent(parent);
		if(items!=null){
			for(MenuItem item:items){
				Element ele = new Element("menu").setAttribute(new Attribute("text", item.getText()));
				root.addContent(ele);
				addMenuItem(item,ele);
			}
		}
	}*/
}
